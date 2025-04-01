package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Booking;
import com.app.CourtReservationSystem.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findAllByAccountId(Long id);
    List<Booking> findAllByCourtId(Long courtId);

    List<Booking> findAllByCourt_IdAndTimeStartBetween(Long courtId, LocalDateTime createdDateAfter, LocalDateTime createdDateBefore);
}
