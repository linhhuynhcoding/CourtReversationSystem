package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {

    @Query("""
            SELECT b
            FROM Booking b 
            WHERE b.orga.id = :org_id AND b.court.id = :court_id 
            AND ((b.timeStart <= :timeStart AND b.timeEnd > :timeStart) 
                                    OR (b.timeStart < :timeEnd AND b.timeEnd >= :timeEnd))
            """)
    List<Booking> findAllBookingByOgranisationAndCourt(Long org_id, Long court_id, LocalDateTime timeStart, LocalDateTime timeEnd);

    @Query("""
            SELECT b
            FROM Booking b 
            WHERE b.orga.id = :org_id
            AND ((b.timeStart <= :timeStart AND b.timeEnd > :timeStart) 
                                    OR (b.timeStart < :timeEnd AND b.timeEnd >= :timeEnd))
            """)
    List<Booking> findAllBookingByOgranisation(Long org_id, LocalDateTime timeStart, LocalDateTime timeEnd);


    Page<Booking> findAllByAccountId(Long id, Pageable pageable);
    List<Booking> findAllByAccountId(Long id);

    List<Booking> findAllByCourtId(Long courtId);

    List<Booking> findAllByCourtIdAndTimeStartBetween(Long courtId, LocalDateTime createdDateAfter, LocalDateTime createdDateBefore);

    List<Booking> findAllByCourtIdAndOrgaIdAndTimeStartBetween
            (Long courtId, Long orgaId, LocalDateTime createdDateAfter, LocalDateTime createdDateBefore);
}
