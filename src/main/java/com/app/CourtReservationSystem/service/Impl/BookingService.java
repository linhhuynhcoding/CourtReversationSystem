package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.booking.BookingFilter;
import com.app.CourtReservationSystem.dto.booking.BookingResponse;
import com.app.CourtReservationSystem.dto.booking.PlaceBookingPayload;
import com.app.CourtReservationSystem.dto.noti.NotiPayload;
import com.app.CourtReservationSystem.enums.*;
import com.app.CourtReservationSystem.exception.APIException;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.BookingMapper;
import com.app.CourtReservationSystem.model.*;
import com.app.CourtReservationSystem.model.relationships.CourtFull;
import com.app.CourtReservationSystem.repository.*;
import com.app.CourtReservationSystem.service.IBookingService;
import com.app.CourtReservationSystem.service.INotificationService;
import com.app.CourtReservationSystem.specification.BookingSpecifications;
import com.app.CourtReservationSystem.specification.CourtSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.CourtReservationSystem.utils.StringUtil.toOrders;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService implements IBookingService {

    BookingRepository bookingRepository;
    PaymentRepository paymentRepository;
    BookingMapper bookingMapper;
    CourtRepository courtRepository;
    OrgaRepository orgaRepository;
    CourtStatusRepository courtStatusRepository;
    INotificationService notificationService;
    private final AccountRepository accountRepository;

    //check 2 khoảng có giao nhau không
    private boolean checkIsIntersected(LocalDateTime timeStart, LocalDateTime timeEnd, LocalDateTime timeBookingStart, LocalDateTime timeBookingEnd) {
        if (timeEnd.isBefore(timeBookingStart) || timeBookingStart.isAfter(timeBookingEnd)) {
            return false;
        }

        return true;
    }

    private void updateIfFull(Organisation org, LocalDateTime date) {
        System.out.println("updateIfFull: " + date);
        // Chuyển lại ngày theo múi giờ +7
        if (date.getHour() >= 17) {
            date = date.plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        } else {
            date = date.withHour(0).withMinute(0).withSecond(0).withNano(0);
        }
        System.out.println("date (UTC+7): " + date);

        List<Booking> bookings = bookingRepository.findAllBookingByOgranisation(
                org.getId(),
                date.minusHours(7), date.plusHours(17) // đưa về khoảng [(17h ngày hôm trước) -> (17h ngày hôm nay)]
        );

        List<Long> courtIds = org.getCourts().stream().map(Court::getId).distinct().toList();

        System.out.println("courtIds: " + courtIds);
        System.out.print("bookings: ");
        for (Booking booking : bookings) {
            System.out.println(booking.getId() + ", ");
        }
        System.out.println("\b\b\n");

        for (double i = 0.0; i <= 23.5; i += 0.5) {
            LocalDateTime startDateBooking = (date.minusHours(7)).plusHours((long) Math.floor(i)).withMinute((int) (i * 60L) - (int) Math.floor(i) * 60);
            LocalDateTime endDateBooking = startDateBooking.plusMinutes(30);

            System.out.println("startDate: " + startDateBooking);
            System.out.println("endDate" + endDateBooking);

            for (var courtId : courtIds) {
                var bookedList = bookings.stream().filter((booking -> {
                    if (!booking.getCourt().getId().equals(courtId)) {
                        return false;
                    }

                    boolean isBooked = checkIsIntersected(startDateBooking, endDateBooking, booking.getTimeStart(), booking.getTimeEnd());

                    return isBooked;
                })).toList();

                if (bookedList.isEmpty()) {
                    return;
                }
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
        } catch (EntityNotFoundException e) {
            throw new APIException("Thông tin booking không hợp lệ!", HttpStatus.BAD_REQUEST);
        }

        if (orgProxy.getStatus() != CourtStatus.OPENING) {
            throw new APIException("Sân đã hết hoặc đang bảo trì!", HttpStatus.BAD_REQUEST);
        }

        booking.setCourt(courtProxy);
        booking.setOrga(orgProxy);
        booking.setAccount(accountProxy);
        booking.setTimeStart(payload.getTimeStart());
        booking.setTimeEnd(timeEnd);
        booking.setTotal(orgProxy.getPrice() * payload.getDuration() * 1.0);

        bookingRepository.save(booking);

        updateIfFull(orgProxy, payload.getTimeStart());

        Booking booking1 = bookingRepository.findById(booking.getId()).orElseThrow(() -> new APIException("Tạo " +
                "booking thất bại", HttpStatus.INTERNAL_SERVER_ERROR));

        // Tạo thông báo đến người dùng
        NotiPayload notiPayload = new NotiPayload();
        notiPayload.setNotiType(NotificationType.BOOKING);
        notiPayload.setTitle("Trạng thái đặt sân!");
        notiPayload.setRole("PLAYER");
        notiPayload.setMessage(orgProxy.getName() + " đang xử lý đơn đặt sân của bạn!");
        notiPayload.setRecipientType(RecipientType.INDIVIDUAL);
        notiPayload.setRecipientId(accountProxy.getId());
        notiPayload.setSenderId(orgProxy.getId());
        notiPayload.setSenderType(SenderType.ORGANISATION);
        notificationService.addNoti(notiPayload);

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
    public List<Booking> getUserTodayBooking(Long accountId) {

        return bookingRepository.findAllByAccountId(accountId);
    }

    @Override
    public List<?> getAllCourtBookings(Long id) {
        return List.of();
    }

    @Override
    public List<?> getAllCourtBookings(Long id, LocalDateTime createdDateAfter, LocalDateTime createdDateBefore) {

        List<Booking> bookings = bookingRepository.findAllByCourtIdAndTimeStartBetween(id, createdDateAfter,
                createdDateBefore);

        return bookingMapper.toDTOs(bookings);
    }

    @Override
    public Page getAllCourtBookings(BookingFilter filter) {
        Specification<Booking> spec = BookingSpecifications.withFilter(filter);

        List<Sort.Order> orders = toOrders(filter.getSort(), BookingSortField.class);
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by(orders));

        Page<Booking> bookings = bookingRepository.findAll(spec, pageable);

        return bookings.map(bookingMapper::toDTO);
    }

    @Override
    @Transactional
    public boolean updateBookingStatus(Long id, BookingStatus status) {
        var booking = bookingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Booking", "id",
                id));

        // Da thanh cong thi khong the thay doi
        if (booking.getStatus() == BookingStatus.BOOKED) {
            return false;
        }

        if (status == BookingStatus.BOOKED && booking.getPayment() == null) {
            // Tạo Payment
            var payment = new Payment();
            payment.setAmount(booking.getTotal());
            payment.setStatus(PaymentStatus.PENDING);
            payment.setMethodPayment(PaymentMethod.COD);
            payment.setBooking(booking);
            if (booking.getOrder() != null) {
                payment.setOrder(booking.getOrder());
            }
            paymentRepository.save(payment);

            booking.setPayment(payment);
        }

        booking.setStatus(status);
        bookingRepository.save(booking);
        return true;
    }
}
