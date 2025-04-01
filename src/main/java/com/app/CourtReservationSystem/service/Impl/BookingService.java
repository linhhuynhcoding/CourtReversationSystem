package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.mapper.BookingMapper;
import com.app.CourtReservationSystem.model.Booking;
import com.app.CourtReservationSystem.repository.BookingRepository;
import com.app.CourtReservationSystem.service.IBookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService implements IBookingService {

    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    
    @Override
    public List<?> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        
        return bookingMapper.toDTOs(bookings);
    }
    
    @Override
    public List<?> getAllUserBookings(Long id) {
        List<Booking> bookings = bookingRepository.findAllByAccountId(id);
        
        return bookingMapper.toDTOs(bookings);
    }

    @Override
    public List<?> getAllCourtBookings(Long id) {
        return List.of();
    }

    @Override
    public List<?> getAllCourtBookings(Long id, LocalDateTime createdDateAfter) {
        LocalDateTime createdDateBefore = createdDateAfter.plusDays(7);
        List<Booking> bookings = bookingRepository.findAllByCourt_IdAndTimeStartBetween(id, createdDateAfter, createdDateBefore);
        
        return bookingMapper.toDTOs(bookings);
    }
}
