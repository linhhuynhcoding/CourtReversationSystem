package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.auth.RegisterPayload;
import com.app.CourtReservationSystem.service.Impl.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    
    @PostMapping("/register")
    private ResponseEntity<String> register(@Valid @RequestBody RegisterPayload registerPayload) {
        String response = authService.register(registerPayload);
        
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }
}
