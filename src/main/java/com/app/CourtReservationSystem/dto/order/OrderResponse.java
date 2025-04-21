package com.app.CourtReservationSystem.dto.order;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.address.AddressResponse;
import com.app.CourtReservationSystem.enums.OrderType;
import com.app.CourtReservationSystem.model.OrderItem;
import com.app.CourtReservationSystem.model.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderResponse {
    private Long id;
    private AccountResponse account;
    private OrderType orderType;
    private Double total;
    private Double productPrice;
    private Payment payment;
    private AddressResponse address;
    private Double shipFee;
    private List<OrderItemResponse> orderItems;
}
