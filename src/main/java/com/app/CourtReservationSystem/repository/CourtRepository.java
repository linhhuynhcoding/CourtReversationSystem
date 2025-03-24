package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import com.app.CourtReservationSystem.model.Court;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {
    @Modifying
    @Transactional
    @Query("""
           UPDATE Court c
           SET
              c.name = CASE WHEN :#{#nc.name} IS NOT NULL THEN :#{#nc.name} ELSE c.name END,
              c.phone = CASE WHEN :#{#nc.phone} IS NOT NULL THEN :#{#nc.phone} ELSE c.phone END,
              c.numberOfCourts = CASE WHEN :#{#nc.numberOfCourts} IS NOT NULL THEN :#{#nc.numberOfCourts} ELSE c.numberOfCourts END,
              c.price = CASE WHEN :#{#nc.price} IS NOT NULL THEN :#{#nc.price} ELSE c.price END,
              c.status = CASE WHEN :#{#nc.status} IS NOT NULL THEN :#{#nc.status} ELSE c.status END
           WHERE c.id = :id
           """)
    int updateCourtById(
        @Param("id") Long id,
        @Param("nc") UpdateCourtPayload nc
    );
}
