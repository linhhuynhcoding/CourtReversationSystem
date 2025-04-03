package com.app.CourtReservationSystem.repository;

import com.app.CourtReservationSystem.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
