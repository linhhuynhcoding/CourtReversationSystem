package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.payment.PaymentResult;
import com.app.CourtReservationSystem.enums.PaymentStatus;
import com.app.CourtReservationSystem.model.Payment;

public interface IPaymentMethodService {
    PaymentStatus process(Double amount);
    PaymentResult process(Payment payment);
}
