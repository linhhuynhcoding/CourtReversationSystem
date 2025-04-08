package com.app.CourtReservationSystem.dto.vietnameProvince;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WardResponse {
    private Long code;
    private String codename;
    private Long district_code;
    private String division_type;
    private String name;
}
