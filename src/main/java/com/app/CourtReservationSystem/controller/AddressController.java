package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.service.IAddressService;
import com.app.CourtReservationSystem.service.IVietnamProviceSerivce;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
    name = "address"
)
public class AddressController {
    
    IAddressService addressService;
    IVietnamProviceSerivce vietnamProviceSerivce;
    
    @Operation(
        summary = "Get All Cities REST API"
    )
    @GetMapping("/cities")
    public ResponseEntity<ApiResponse<?>> getCities(
        HttpServletRequest request
    ) {
        List<?> responses = vietnamProviceSerivce.getAllCities();
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
            request.getRequestURI(), responses));
    }
    
    @Operation(
        summary = "Get All Districts by city_code REST API"
    )
    @GetMapping("/districts/{city_code}")
    public ResponseEntity<ApiResponse<?>> getDistricts(
        HttpServletRequest request,
        @PathVariable Long city_code
        
    ) {
        List<?> responses = vietnamProviceSerivce.getAllDistricts(city_code);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
            request.getRequestURI(), responses));
    }
    
    @Operation(
        summary = "Get All Wards by district_code REST API"
    )
    @GetMapping("/wards/{district_code}")
    public ResponseEntity<ApiResponse<?>> getAccount(
        HttpServletRequest request,
        @PathVariable Long district_code
    ) {
        List<?> responses = vietnamProviceSerivce.getAllWards(district_code);
        
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
            request.getRequestURI(), responses));
    }
}
