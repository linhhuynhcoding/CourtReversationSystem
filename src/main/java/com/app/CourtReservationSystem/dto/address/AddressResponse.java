package com.app.CourtReservationSystem.dto.address;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {
    private Long id;
    private String city;
    private String district;
    private String ward;
    private String addressLine;
    private Long latitude;
    private Long longitude;
}
