package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ImageRepository extends JpaRepository<Image, Long> {
}
