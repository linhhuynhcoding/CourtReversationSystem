package com.app.CourtReservationSystem.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private String tokenType = "access_token";
}
