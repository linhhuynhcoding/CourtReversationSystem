package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.service.IBookingService;
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
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
    name = "booking"
)public class BookingController {
    IBookingService bookingService;
    
    @Operation(summary = "Get All Booking REST API")
    @GetMapping("/bookings")
    public ResponseEntity<ApiResponse<?>> getAllBookings(HttpServletRequest request) {
        List<?> response = bookingService.getAllBookings();
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }
    
    @Operation(summary = "Get All User Booking REST API")
    @GetMapping("/accounts/{id}/bookings")
    public ResponseEntity<ApiResponse<?>> getAllUserBookings(HttpServletRequest request, @PathVariable("id") Long id) {
        List<?> response = bookingService.getAllUserBookings(id);
        
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }
    
    @Operation(summary = "Get All Court Booking REST API")
    @GetMapping("/courts/{id}/bookings")
    public ResponseEntity<ApiResponse<?>> getAllCourtBookings(HttpServletRequest request, @PathVariable("id") Long id) {
        List<?> response = bookingService.getAllCourtBookings(id);
        
        return ResponseEntity.status(HttpStatus.OK)
            .body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }
}
