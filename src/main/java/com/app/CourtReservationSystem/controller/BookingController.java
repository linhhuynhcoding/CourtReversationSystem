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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "booking")
public class BookingController {
    IBookingService bookingService;

    @Operation(summary = "Get All Booking REST API")
    @GetMapping("/bookings")
    public ResponseEntity<ApiResponse<?>> getAllBookings(HttpServletRequest request) {
        List<?> response = bookingService.getAllBookings();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }

    @Operation(summary = "Get All User Booking REST API")
    @GetMapping("/accounts/{id}/bookings")
    public ResponseEntity<ApiResponse<?>> getAllUserBookings(HttpServletRequest request, @PathVariable("id") Long id) {
        List<?> response = bookingService.getAllUserBookings(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }

    @Operation(summary = "Get All Court Booking REST API")
    @GetMapping("/courts/{id}/bookings")
    public ResponseEntity<ApiResponse<?>> getAllCourtBookings(
            HttpServletRequest request,
            @PathVariable("id") Long id,
            @RequestParam(name = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date // ISO 8601 format
    ) {
        date.withHour(0).withMinute(0).withSecond(0).withNano(0);

        List<?> response = bookingService.getAllCourtBookings(id, date);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }
}
