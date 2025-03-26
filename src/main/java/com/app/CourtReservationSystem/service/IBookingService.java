package com.app.CourtReservationSystem.service;

import java.util.List;

public interface IBookingService {
    List<?> getAllBookings();
    List<?> getAllUserBookings(Long id);
    List<?> getAllCourtBookings(Long id);
}
