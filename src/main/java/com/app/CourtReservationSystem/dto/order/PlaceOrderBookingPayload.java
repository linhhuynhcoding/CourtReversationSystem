package com.app.CourtReservationSystem.dto.order;

import com.app.CourtReservationSystem.dto.address.CreateAddressPayload;
import com.app.CourtReservationSystem.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PlaceOrderBookingPayload {
    private Long bookingId;
    private List<Item> items;

}
