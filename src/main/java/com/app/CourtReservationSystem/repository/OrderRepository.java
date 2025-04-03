package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page findAllByAccountId(Long accountId, Pageable pageable);
}
