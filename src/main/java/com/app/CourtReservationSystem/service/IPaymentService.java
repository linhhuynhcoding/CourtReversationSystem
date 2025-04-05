package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.payment.PaymentPayload;
import com.app.CourtReservationSystem.model.Payment;

public interface IPaymentService {

    Payment createPayment(PaymentPayload payload);

//    void handlePaymentOrder(PaymentPayload payload);
//    void handlePaymentBooking(PaymentPayload payload);
}
