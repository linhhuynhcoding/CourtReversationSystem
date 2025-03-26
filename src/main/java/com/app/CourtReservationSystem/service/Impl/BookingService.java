package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.mapper.BookingMapper;
import com.app.CourtReservationSystem.model.Booking;
import com.app.CourtReservationSystem.repository.BookingRepository;
import com.app.CourtReservationSystem.service.IBookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

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
        List<Booking> bookings = bookingRepository.findAllByCourtId(id);
        
        return bookingMapper.toDTOs(bookings);
    }
}
