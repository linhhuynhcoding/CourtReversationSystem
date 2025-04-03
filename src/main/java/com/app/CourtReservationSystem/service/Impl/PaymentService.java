package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.payment.PaymentPayload;
import com.app.CourtReservationSystem.enums.PaymentMethod;
import com.app.CourtReservationSystem.model.Payment;
import com.app.CourtReservationSystem.repository.PaymentRepository;
import com.app.CourtReservationSystem.service.IPaymentMethodService;
import com.app.CourtReservationSystem.service.IPaymentService;
import jakarta.transaction.Transactional;
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
        paymentMethodServiceMap = new HashMap<>();
        this.paymentMethodServiceMap.put(PaymentMethod.VNPAY, vnpayService);
        this.paymentMethodServiceMap.put(PaymentMethod.COD, codSerivce);
    }

    @Override
    @Transactional
    public Payment createPayment(PaymentPayload payload) {
        Payment payment = new Payment();
        payment.setAmount(payload.getAmount());
        payment.setMethodPayment(payload.getPaymentMethod());
        paymentRepository.save(payment);
        return payment;
    }
}
