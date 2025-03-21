package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.jwt.JwtTokenPovider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @GetMapping("/register")
    private String register() {
        return "";
    }
}
