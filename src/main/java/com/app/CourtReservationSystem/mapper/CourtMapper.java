package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.court.CourtResponse;
import com.app.CourtReservationSystem.model.Court;
import org.mapstruct.Mapper;

@Mapper(componentModel =  "spring")
public interface CourtMapper {
    
    CourtResponse toDTO(Court court);
}
