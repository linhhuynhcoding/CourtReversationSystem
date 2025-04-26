package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.BookingRevenueStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BookingRevenueStatsRepository extends JpaRepository<BookingRevenueStats, Long> {
}
