package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.relationships.CourtFull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtStatusRepository extends JpaRepository<CourtFull, Long> {

}
