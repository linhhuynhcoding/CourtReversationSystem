package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {

    @Query("""
            SELECT SUM(r.total) FROM Revenue r WHERE r.date >= :fromDate AND r.date <= :toDate
            """)
    Double getTotalRevenueBooking(LocalDateTime fromDate, LocalDateTime toDate);
}
