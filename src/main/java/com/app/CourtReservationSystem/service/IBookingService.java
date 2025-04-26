package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.booking.BookingFilter;
import com.app.CourtReservationSystem.dto.booking.BookingResponse;
import com.app.CourtReservationSystem.dto.booking.PlaceBookingPayload;
import com.app.CourtReservationSystem.enums.BookingStatus;
import com.app.CourtReservationSystem.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IBookingService {

    BookingResponse createBooking(PlaceBookingPayload payload);
    List<?> getAllBookings();
    Page getAllUserBookings(Long id, Pageable pageable);
    List<Booking> getUserTodayBooking(Long accountId);
    List<?> getAllCourtBookings(Long id);
    List<?> getAllCourtBookings(Long id, LocalDateTime dateStart, LocalDateTime localDateTime);
    Page getAllCourtBookings(BookingFilter filter);
    boolean updateBookingStatus(Long id, BookingStatus status);
}
