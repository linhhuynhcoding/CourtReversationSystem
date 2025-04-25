package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.CustomerStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerStatsRepository extends JpaRepository<CustomerStats, Long> {
}
