package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
