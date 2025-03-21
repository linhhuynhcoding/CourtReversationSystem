package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.auth.RegisterPayload;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthService {

  public String register(RegisterPayload registerPayload);
}
