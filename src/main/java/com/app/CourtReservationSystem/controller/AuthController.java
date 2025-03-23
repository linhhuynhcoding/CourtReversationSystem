package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.auth.LoginPayload;
import com.app.CourtReservationSystem.dto.auth.RegisterPayload;
import com.app.CourtReservationSystem.dto.auth.TokenResponse;
import com.app.CourtReservationSystem.service.IAuthService;
import com.app.CourtReservationSystem.service.Impl.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
    name = "auth"
)
public class AuthController {

    IAuthService authService;
    
    @PostMapping("/login")
    private ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginPayload loginPayload) {
        
        String token = authService.login(loginPayload);
        
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        
        return ResponseEntity.ok(tokenResponse);
    }
    
    @PostMapping("/register")
    private ResponseEntity<String> register(@Valid @RequestBody RegisterPayload registerPayload) {
        String response = authService.register(registerPayload);
        
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }
    
    @PostMapping("/logout")
    private ResponseEntity<String> logout() {
        // TODO call logout service
        String response = "";
        
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }
}
