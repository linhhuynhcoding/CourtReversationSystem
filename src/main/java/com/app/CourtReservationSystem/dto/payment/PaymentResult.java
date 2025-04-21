package com.app.CourtReservationSystem.dto.payment;

import com.app.CourtReservationSystem.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResult {
    PaymentResponse payment;
    private PaymentStatus status = PaymentStatus.PENDING;
    private boolean redirect = false;
    private String redirectUrl = "";
}
