package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.court.CourtResponse;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.CourtMapper;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.repository.CourtRepository;
import com.app.CourtReservationSystem.service.ICourtService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourtService implements ICourtService {
    CourtRepository courtRepository;
    CourtMapper courtMapper;
    
    @Override
    public CourtResponse getCourt(Long id) {
        Court court = courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id", id));
        
        return courtMapper.toDTO(court);
    }
}
