package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.auth.LoginPayload;
import com.app.CourtReservationSystem.dto.auth.RegisterPayload;
import com.app.CourtReservationSystem.dto.auth.TokenResponse;
import com.app.CourtReservationSystem.security.CustomUserDetails;
import com.app.CourtReservationSystem.service.IAuthService;
import com.app.CourtReservationSystem.service.Impl.AuthService;
import com.cloudinary.Api;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @GetMapping("/me")
    @SecurityRequirement(name = "Bear Authentication")
    private ResponseEntity<ApiResponse> me(HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long accountId = ((CustomUserDetails) auth.getPrincipal()).getId();

        AccountResponse response = authService.me(accountId);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }
    
    @PostMapping("/login")
    private ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginPayload loginPayload, HttpServletRequest request) {
        
        String token = authService.login(loginPayload);
        
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(token);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), tokenResponse));

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
