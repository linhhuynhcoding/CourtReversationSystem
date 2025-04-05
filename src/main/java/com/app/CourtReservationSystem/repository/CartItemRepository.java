package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
    Optional<CartItem> findByIdAndCartId(Long id, Long cartId);

    List<CartItem> findByCartIdAndSelectedIsTrue(Long cartId);
    void deleteById(Long id);

}
