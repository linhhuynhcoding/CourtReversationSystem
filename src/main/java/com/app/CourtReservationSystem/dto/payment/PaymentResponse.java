package com.app.CourtReservationSystem.dto.payment;

import com.app.CourtReservationSystem.dto.booking.BookingResponse;
import com.app.CourtReservationSystem.dto.order.OrderResponse;
import com.app.CourtReservationSystem.enums.PaymentMethod;
import com.app.CourtReservationSystem.enums.PaymentStatus;
import com.app.CourtReservationSystem.model.Booking;
import com.app.CourtReservationSystem.model.Order;
import lombok.Getter;
import lombok.Setter;

@Getter()
@Setter()
public class PaymentResponse {
    private Long id;
    private Double amount;
    private PaymentStatus status = PaymentStatus.PENDING;
    private PaymentMethod methodPayment;
    private BookingResponse booking;
    private OrderResponse order;
}
