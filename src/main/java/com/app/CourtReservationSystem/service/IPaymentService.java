package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.enums.PaymentMethod;
import com.app.CourtReservationSystem.model.Payment;

public interface IPaymentService {

    Payment processPayment(PaymentMethod paymentMethod, Double amount);
}
