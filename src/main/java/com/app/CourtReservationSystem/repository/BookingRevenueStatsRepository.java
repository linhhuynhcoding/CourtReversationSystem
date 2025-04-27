package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.BookingRevenueStats;
import com.app.CourtReservationSystem.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface BookingRevenueStatsRepository extends JpaRepository<BookingRevenueStats, Long> {
    Optional<BookingRevenueStats> findByDate(LocalDateTime date);

    @Query("SELECT b FROM BookingRevenueStats b WHERE b.organisation = ?1 AND b.date = ?2")
    Optional<BookingRevenueStats> getBookingRevenue(Organisation orga, LocalDateTime date);
}
