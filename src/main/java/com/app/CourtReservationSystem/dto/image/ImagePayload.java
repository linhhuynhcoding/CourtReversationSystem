package com.app.CourtReservationSystem.dto.image;

import com.app.CourtReservationSystem.enums.ResolutionType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImagePayload {
    private String image_url;
    private Long width;
    private Long height;
    private ResolutionType type;
}
