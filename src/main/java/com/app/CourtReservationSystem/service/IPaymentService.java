package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.payment.PaymentPayload;
import com.app.CourtReservationSystem.dto.payment.PaymentResult;
import com.app.CourtReservationSystem.model.Payment;
import jakarta.servlet.http.HttpServletRequest;

public interface IPaymentService {

    Payment createPayment(PaymentPayload payload);
    PaymentResult handlePaymentBooking(HttpServletRequest request, PaymentPayload payload);
//    void handlePaymentOrder(PaymentPayload payload);
//    void handlePaymentBooking(PaymentPayload payload);
}
