package com.app.CourtReservationSystem.dto.order;

import com.app.CourtReservationSystem.dto.address.CreateAddressPayload;
import com.app.CourtReservationSystem.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlaceOrderPayload {
    private CreateAddressPayload createAddressPayload;
    private PaymentMethod paymentMethod;
}
