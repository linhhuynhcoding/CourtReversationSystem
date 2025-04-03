package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.enums.PaymentMethod;
import com.app.CourtReservationSystem.model.Payment;
import com.app.CourtReservationSystem.repository.PaymentRepository;
import com.app.CourtReservationSystem.service.IPaymentMethodService;
import com.app.CourtReservationSystem.service.IPaymentService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService implements IPaymentService {
    private Map<PaymentMethod, IPaymentMethodService> paymentMethodServiceMap;
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository, VNPAYService vnpayService, CODSerivce codSerivce) {
        this.paymentRepository = paymentRepository;
        this.paymentMethodServiceMap.put(PaymentMethod.VNPAY, vnpayService);
        this.paymentMethodServiceMap.put(PaymentMethod.COD, codSerivce);
    }

    @Override
    public Payment processPayment(PaymentMethod paymentMethod, Double amount) {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setMethodPayment(paymentMethod);

        payment.setStatus(this.paymentMethodServiceMap.get(paymentMethod).process(amount));
        paymentRepository.save(payment);
        return payment;
    }
}
