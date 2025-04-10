package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.payment.PaymentResponse;
import com.app.CourtReservationSystem.enums.PaymentStatus;
import com.app.CourtReservationSystem.model.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentResponse toDTO(Payment payment);
}
