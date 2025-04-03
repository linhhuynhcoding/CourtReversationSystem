package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.enums.PaymentStatus;
import com.app.CourtReservationSystem.service.IPaymentMethodService;
import com.app.CourtReservationSystem.service.IPaymentService;
import org.springframework.stereotype.Service;

@Service
public class CODSerivce implements IPaymentMethodService {
    @Override
    public PaymentStatus process(Double amount) {
        return PaymentStatus.SUCCESS;
    }
}
