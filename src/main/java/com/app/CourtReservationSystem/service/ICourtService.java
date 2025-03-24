package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.court.CourtResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;

import java.util.List;

public interface ICourtService {
    
    CourtResponse getCourt(Long id);
    CourtResponse updateCourt(Long id, UpdateCourtPayload updateCourtPayload);
    CourtResponse createCourt(CreateCourtPayload createCourtPayload);
    void deleteCourt(Long id);
    List<CourtResponse> getAllCourts();
}
