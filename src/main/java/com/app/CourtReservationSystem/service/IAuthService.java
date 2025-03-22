package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.auth.LoginPayload;
import com.app.CourtReservationSystem.dto.auth.RegisterPayload;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthService {
  
  public String login(LoginPayload loginPayload);
  
  public String register(RegisterPayload registerPayload);
  
  public String logout();
}
