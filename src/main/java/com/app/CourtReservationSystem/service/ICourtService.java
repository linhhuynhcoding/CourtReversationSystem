package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.court.CourtResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface ICourtService {
    
    CourtResponse getCourt(Long id);
    CourtResponse getCourt(Long id, Date startFrom);
    CourtResponse updateCourt(Long id, UpdateCourtPayload updateCourtPayload);
    CourtResponse createCourt(CreateCourtPayload createCourtPayload);
    void deleteCourt(Long id);
    Page getAllCourts(Pageable pageable);
}
