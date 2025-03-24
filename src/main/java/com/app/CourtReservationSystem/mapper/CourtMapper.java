package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.court.CourtResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import com.app.CourtReservationSystem.model.Court;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel =  "spring")
public interface CourtMapper {
    
    @Mapping(ignore = true, target="imageCourts")
    @Mapping(ignore = true, target="status")
    Court createToEntity(CreateCourtPayload createCourtPayload);
    
    @Mapping(ignore = true, target="imageCourts")
    Court updateToEntity(UpdateCourtPayload updateCourtPayload);
    
    CourtResponse toDTO(Court court);
    List<CourtResponse> toDTOs (List<Court> courts);
}
