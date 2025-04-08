package com.app.CourtReservationSystem.dto.court;

import com.app.CourtReservationSystem.dto.address.AddressResponse;
import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrgaResponse {
    private Long id;
    private String name;
    private String phone;
    private Long numberOfCourts;
    private Double price;
    private AddressResponse address;
    private CourtStatus status;
    private List<ImageCourt> imageCourts;
    private List<CourtResponse> courts;
}
