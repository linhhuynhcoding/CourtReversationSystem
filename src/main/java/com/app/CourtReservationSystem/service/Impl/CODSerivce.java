package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.payment.PaymentResult;
import com.app.CourtReservationSystem.enums.PaymentStatus;
import com.app.CourtReservationSystem.mapper.PaymentMapper;
import com.app.CourtReservationSystem.model.Payment;
import com.app.CourtReservationSystem.repository.PaymentRepository;
import com.app.CourtReservationSystem.service.IPaymentMethodService;
import com.app.CourtReservationSystem.service.IPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CODSerivce implements IPaymentMethodService {
    PaymentRepository paymentRepository;
    PaymentMapper paymentMapper;

    @Override
    public PaymentStatus process(Double amount) {
        return PaymentStatus.SUCCESS;
    }

    @Override
    @Transactional
    public PaymentResult process(HttpServletRequest request, Payment payment) {
        payment.setStatus(PaymentStatus.SUCCESS);
        paymentRepository.save(payment);

        var result = new PaymentResult();
        result.setPayment(paymentMapper.toDTO(payment));
        result.setStatus(PaymentStatus.SUCCESS);

        return result;
    }
}
