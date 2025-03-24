package com.app.CourtReservationSystem.dto.vietnameProvince;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DistrictResponse<W> {
    private String name;
    private Long code;
    private String division_type;
    private String codename;
    private Long province_code;
    private List<W> wards;
}
