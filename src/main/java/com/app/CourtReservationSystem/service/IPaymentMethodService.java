package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.enums.PaymentStatus;

public interface IPaymentMethodService {
    PaymentStatus process(Double amount);
}
