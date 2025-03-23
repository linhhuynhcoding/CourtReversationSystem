package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.court.CourtResponse;

public interface ICourtService {
    
    CourtResponse getCourt(Long id);
//    CourtResponse updateCourt();
}
