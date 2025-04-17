package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.court.OrgaResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import com.app.CourtReservationSystem.dto.image.ImageResponse;
import com.app.CourtReservationSystem.model.Organisation;
import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel =  "spring")
public interface CourtMapper {
    
//    @Mapping(ignore = true, target="imageCourts")
    @Mapping(ignore = true, target="status")
    Organisation createToEntity(CreateCourtPayload createCourtPayload);
    
//    @Mapping(ignore = true, target="imageCourts")
    Organisation updateToEntity(UpdateCourtPayload updateCourtPayload);
    OrgaResponse toDTO(Organisation court);
    List<OrgaResponse> toDTOs (List<Organisation> courts);

    @Mapping(source = "imageCourt.image.image_url", target = "image_url")
    @Mapping(source = "imageCourt.image.width", target = "width")
    @Mapping(source = "imageCourt.image.height", target = "height")
    @Mapping(source = "imageCourt.image.status", target = "status")
    @Mapping(source = "imageCourt.image.type", target = "type")
    @Mapping(target = "imageCourt.image.id", ignore = true)
    ImageResponse imageCourtToImageResponse(ImageCourt imageCourt);

    List<ImageResponse> imageCourtListToImageResponseList(List<ImageCourt> images);
}
