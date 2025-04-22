package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
