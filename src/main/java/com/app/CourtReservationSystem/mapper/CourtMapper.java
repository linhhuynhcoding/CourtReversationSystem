package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.court.OrgaResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import com.app.CourtReservationSystem.model.Organisation;
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
}
