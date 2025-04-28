package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.dto.statistic.RevenueBookingLocation;
import com.app.CourtReservationSystem.model.BookingRevenueStats;
import com.app.CourtReservationSystem.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRevenueStatsRepository extends JpaRepository<BookingRevenueStats, Long> {
    Optional<BookingRevenueStats> findByDate(LocalDateTime date);

    @Query("SELECT b FROM BookingRevenueStats b WHERE b.organisation = ?1 AND b.date = ?2")
    Optional<BookingRevenueStats> getBookingRevenue(Organisation orga, LocalDateTime date);

    @Query("""
            SELECT SUM(r.totalRevenue) FROM BookingRevenueStats r WHERE r.date >= :fromDate AND r.date <= :toDate
            """)
    Double getTotalRevenueBooking(LocalDateTime fromDate, LocalDateTime toDate);

    @Query("""
            SELECT new com.app.CourtReservationSystem.dto.statistic.RevenueBookingLocation(SUM(r.totalRevenue), SUM(r.totalBookings), r.city)
                        FROM BookingRevenueStats r WHERE r.date >= :fromDate AND r.date <= :toDate GROUP BY r.city
            """)
    List<RevenueBookingLocation> getRevenueGroupByCity(LocalDateTime fromDate, LocalDateTime toDate);
}
