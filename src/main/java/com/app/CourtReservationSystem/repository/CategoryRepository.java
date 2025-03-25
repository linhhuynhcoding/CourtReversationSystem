package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
