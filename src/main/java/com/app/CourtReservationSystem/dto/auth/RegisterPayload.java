package com.app.CourtReservationSystem.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPayload {
  @NotEmpty(message = "Should not empty!")
  private String username;
  @NotEmpty(message = "Should not empty!")
  private String email;
  @NotEmpty(message = "Should not empty!")
  private String name;
  @NotEmpty(message = "Should not empty!")
  private String password;
}

