package com.app.CourtReservationSystem.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResult {
    PaymentResponse payment;

    private boolean redirect = false;
    private String redirectUrl = "";
}
