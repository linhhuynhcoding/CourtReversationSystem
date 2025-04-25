package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.UsageStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageStatsRepository extends JpaRepository<UsageStats, Long> {
}
