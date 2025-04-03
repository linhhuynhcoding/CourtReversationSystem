package com.app.CourtReservationSystem.dto.payment;

import com.app.CourtReservationSystem.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentPayload {
    private Double amount;
    private PaymentMethod paymentMethod;
}
