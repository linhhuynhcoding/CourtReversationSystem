package com.app.CourtReservationSystem.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter()
@Setter()
public class PaymentResponse {
    private String redirectURL = null;
    Object response = null;
}
