package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.booking.BookingResponse;
import com.app.CourtReservationSystem.dto.booking.PlaceBookingPayload;
import com.app.CourtReservationSystem.enums.BookingStatus;
import com.app.CourtReservationSystem.exception.APIException;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.BookingMapper;
import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.model.Booking;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.model.Organisation;
import com.app.CourtReservationSystem.model.relationships.CourtFull;
import com.app.CourtReservationSystem.repository.*;
import com.app.CourtReservationSystem.service.IBookingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService implements IBookingService {

    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    CourtRepository courtRepository;
    OrgaRepository orgaRepository;
    CourtStatusRepository courtStatusRepository;
    private final AccountRepository accountRepository;

    private void updateIfFull(Organisation org, LocalDateTime date) {
        date = date.withHour(0).withMinute(0).withSecond(0).withNano(0);

        for (double i = 0.0; i <= 23.5; i += 0.5) {
            long hour = Math.round(Math.floor(i));
            long minute = Math.round(((i - hour) * 60));
            LocalDateTime startDate = date.withHour((int) hour).withMinute((int) minute);
            LocalDateTime endDate = startDate.plusMinutes((long) (0.5 * 60));

            System.out.println("startDate: " + startDate);
            System.out.println("endDate" + endDate);

            List<Booking> bookings = bookingRepository.findAllBookingByOgranisation(
                    org.getId(),
                    startDate, endDate
            );
            System.out.println("Bookings: " + bookings.size());
            System.out.println("NumberOfCourts: " + org.getNumberOfCourts());
            System.out.println("___________________");

            if (bookings.size() < org.getNumberOfCourts()) {
                return;
            }
        }

        var cS = new CourtFull();
        cS.setDate(date);
        cS.setOrganisation(org);
        courtStatusRepository.save(cS);
    }

    @Override
    @Transactional
    public BookingResponse createBooking(PlaceBookingPayload payload) {

        LocalDateTime timeEnd = payload.getTimeStart().plusMinutes((long) (payload.getDuration() * 60));

        List<Booking> bookings = bookingRepository.findAllBookingByOgranisationAndCourt(
                payload.getOrgaId(), payload.getCourtId(),
                payload.getTimeStart(), timeEnd
                );

        if (!bookings.isEmpty()) {
            throw new APIException("Booking đã tồn tại, vui lòng thay đổi thông tin booking!", HttpStatus.BAD_REQUEST);
        }

        Booking booking = new Booking();
        Court courtProxy = courtRepository.getReferenceById(payload.getCourtId());
        Organisation orgProxy = orgaRepository.getReferenceById(payload.getOrgaId());
        Account accountProxy = accountRepository.getReferenceById(payload.getAccountId());
        try {
            courtProxy.getId();
            orgProxy.getId();
            accountProxy.getId();
        }
        catch (EntityNotFoundException e) {
            throw new APIException("Thông tin booking không hợp lệ!", HttpStatus.BAD_REQUEST);
        }

        booking.setCourt(courtProxy);
        booking.setOrga(orgProxy);
        booking.setAccount(accountProxy);
        booking.setTimeStart(payload.getTimeStart());
        booking.setTimeEnd(timeEnd);
        booking.setTotal(orgProxy.getPrice() * payload.getDuration() * 1.0);

        bookingRepository.save(booking);

        updateIfFull(orgProxy, payload.getTimeStart());

        Booking booking1 = bookingRepository.findById(booking.getId()).orElseThrow(() -> new APIException("Tạo booking thất bại", HttpStatus.INTERNAL_SERVER_ERROR));
        return bookingMapper.toDTO(booking1);
    }

    @Override
    public List<?> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();

        return bookingMapper.toDTOs(bookings);
    }

    @Override
    public Page getAllUserBookings(Long id, Pageable pageable) {
        Page<Booking> bookings = bookingRepository.findAllByAccountId(id, pageable);

        return bookings.map(bookingMapper::toDTO);
    }

    @Override
    public List<?> getAllCourtBookings(Long id) {
        return List.of();
    }

    @Override
    public List<?> getAllCourtBookings(Long id, LocalDateTime createdDateAfter, LocalDateTime createdDateBefore) {

        List<Booking> bookings = bookingRepository.findAllByCourtIdAndTimeStartBetween(id, createdDateAfter, createdDateBefore);

        return bookingMapper.toDTOs(bookings);
    }

    @Override
    @Transactional
    public void updateBookingStatus(Long id, BookingStatus status) {
        var booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking", "id", id));

        booking.setStatus(status);
        bookingRepository.save(booking);
    }
}
