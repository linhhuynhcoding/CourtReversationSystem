package com.app.CourtReservationSystem.dto.court;

import com.app.CourtReservationSystem.model.Court;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CourtResponse {
    private Long id;

    private String name;
}
