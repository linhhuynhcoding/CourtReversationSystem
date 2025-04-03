package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findAllByAccountId(Long id);
    List<Booking> findAllByCourtId(Long courtId);

    List<Booking> findAllByCourtIdAndTimeStartBetween(Long courtId, LocalDateTime createdDateAfter, LocalDateTime createdDateBefore);
}
