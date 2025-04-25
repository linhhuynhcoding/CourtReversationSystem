package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenueRepository extends JpaRepository<Revenue, Long> {
}
