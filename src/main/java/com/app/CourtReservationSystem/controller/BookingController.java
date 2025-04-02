package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.service.IBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateStart, // ISO 8601 format
            @Valid @Max(7) @Min(1) Integer duration  // ISO 8601 format
    ) {
        duration = duration == null ? 7 : duration;
        dateStart.withHour(0).withMinute(0).withSecond(0).withNano(0);

        List<?> response = bookingService.getAllCourtBookings(id, dateStart, dateStart.plusDays(duration));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }
}
