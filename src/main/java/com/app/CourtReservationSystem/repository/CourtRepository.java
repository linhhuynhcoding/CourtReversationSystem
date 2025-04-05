package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Long> {
}
