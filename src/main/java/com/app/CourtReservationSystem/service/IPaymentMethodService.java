package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.payment.PaymentResult;
import com.app.CourtReservationSystem.enums.PaymentStatus;
import com.app.CourtReservationSystem.model.Payment;
import jakarta.servlet.http.HttpServletRequest;

import java.net.http.HttpRequest;

public interface IPaymentMethodService {
    PaymentStatus process(Double amount);
    PaymentResult process(HttpServletRequest request, Payment payment);
    void validatePayment(HttpServletRequest request);

}
