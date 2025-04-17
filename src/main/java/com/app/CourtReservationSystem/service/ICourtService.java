package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.court.CourtFilter;
import com.app.CourtReservationSystem.dto.court.OrgaResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.UpdateCourtPayload;
import com.app.CourtReservationSystem.model.Organisation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.time.LocalDateTime;

public interface ICourtService {

    OrgaResponse getCourt(Long id);
    OrgaResponse getCourt(Long id, Date startFrom);
    OrgaResponse updateCourt(Long id, UpdateCourtPayload updateCourtPayload);
    OrgaResponse createCourt(CreateCourtPayload createCourtPayload);
    void deleteCourt(Long id);
    Page getAllCourts(Pageable pageable);
    Page getAllCourts(CourtFilter filter);
}
