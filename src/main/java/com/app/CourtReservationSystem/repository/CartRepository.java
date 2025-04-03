package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByIdAndAccountId(Long cartId, Long accountId);

}
