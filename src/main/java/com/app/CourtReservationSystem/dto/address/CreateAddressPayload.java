package com.app.CourtReservationSystem.dto.address;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAddressPayload {
    private String city;
    private String district;
    private String ward;
    private String addressLine;
    private Long latitude;
    private Long longitude;
}

