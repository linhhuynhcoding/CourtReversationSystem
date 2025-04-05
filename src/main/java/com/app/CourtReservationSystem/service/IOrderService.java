package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.order.OrderResponse;
import com.app.CourtReservationSystem.dto.order.PlaceOrderBookingPayload;
import com.app.CourtReservationSystem.dto.order.PlaceOrderPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    OrderResponse placeOrder(Long accountId, PlaceOrderPayload payload);
    OrderResponse placeOrderBooking(Long accountId, Long bookingId, PlaceOrderBookingPayload payload);
    OrderResponse getOrder(Long id);
    Page getAccountOrders(Long id, Pageable pageable);
    Page getOrders(Pageable pageable);
}
