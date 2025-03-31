package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.court.CourtResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.CourtMapper;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.repository.CourtRepository;
import com.app.CourtReservationSystem.service.ICourtService;
import com.app.CourtReservationSystem.service.IStorageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourtService implements ICourtService {
    CourtRepository courtRepository;
    CourtMapper courtMapper;
    IStorageService fileStorageService;
    
    static Long SEVEN_DAYS_TIMESTAMP = 1000 * 3600 * 24 * 7L;
    
    @Override
    public CourtResponse getCourt(Long id) {
        Court court = courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id", id));
        
        return courtMapper.toDTO(court);
    }
    
    @Override
    public CourtResponse getCourt(Long id, Date startFrom) {
        Date endAt = new Date(startFrom.getTime() + SEVEN_DAYS_TIMESTAMP);
        Court court = courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id", id));
        
        return courtMapper.toDTO(court);
    }
    
    @Override
    public CourtResponse updateCourt(Long id, UpdateCourtPayload updateCourtPayload) {
        courtRepository.updateCourtById(id, updateCourtPayload);
        Court court = courtRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Court", "id", id));
        
        // TODO Xu ly anh
        // ...
        
        // TODO Xu ly dia chi
        // ...
        
        return courtMapper.toDTO(court);
    }
    
    @Override
    public CourtResponse createCourt(CreateCourtPayload createCourtPayload) {
        Court court = courtMapper.createToEntity(createCourtPayload);
        
        court.setStatus(CourtStatus.OPENING);
        courtRepository.save(court);
        
        // TODO Xu ly anh
        // ...
        return courtMapper.toDTO(court);
    }
    
    @Override
    public void deleteCourt(Long id) {
        courtRepository.deleteById(id);
    }
    
    @Override
    public List<CourtResponse> getAllCourts() {
        List<Court> courts = courtRepository.findAll();
        return courtMapper.toDTOs(courts);
    }
}
