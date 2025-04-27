package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.dto.statistic.RevenueBookingLocation;
import com.app.CourtReservationSystem.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    @Query("""
            SELECT SUM(r.totalRevenue) FROM Revenue r WHERE r.date >= :fromDate AND r.date <= :toDate
            """)
    Double getTotalRevenueBooking(LocalDateTime fromDate, LocalDateTime toDate);

    @Query("""
            SELECT new com.app.CourtReservationSystem.dto.statistic.RevenueBookingLocation(SUM(r.totalRevenue), SUM(r.totalBookings), r.city)
                        FROM Revenue r WHERE r.date >= :fromDate AND r.date <= :toDate GROUP BY r.city
            """)
    List<RevenueBookingLocation> getRevenueGroupByCity(LocalDateTime fromDate, LocalDateTime toDate);
}
