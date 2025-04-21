package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.booking.BookingFilter;
import com.app.CourtReservationSystem.dto.booking.BookingResponse;
import com.app.CourtReservationSystem.dto.booking.PlaceBookingPayload;
import com.app.CourtReservationSystem.dto.court.CourtFilter;
import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.enums.BookingSortField;
import com.app.CourtReservationSystem.enums.ProductSortField;
import com.app.CourtReservationSystem.security.CustomUserDetails;
import com.app.CourtReservationSystem.service.IBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.CourtReservationSystem.utils.StringUtil.toOrders;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "booking")
public class BookingController {
    IBookingService bookingService;

    @Operation(summary = "Get All Booking REST API")
    @GetMapping("/bookings")
    public ResponseEntity<ApiResponse<?>> getAllBookings(HttpServletRequest request, CourtFilter courtFilter) {
        List<?> response = bookingService.getAllBookings();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }

    @Operation(summary = "Get All User Booking REST API")
    @GetMapping("/accounts/{id}/bookings")
    public ResponseEntity<ApiResponse<?>> getAllUserBookings(HttpServletRequest request,
                                                             @PathVariable("id") Long id, @Valid BookingFilter filter) {
        List<Sort.Order> orders = toOrders(filter.getSort(), BookingSortField.class);
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by(orders));

        Page response = bookingService.getAllUserBookings(id, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }

    @Operation(summary = "Get All Court Booking REST API")
    @GetMapping("/courts/{id}/bookings")
    public ResponseEntity<ApiResponse<?>> getAllCourtBookings(
            HttpServletRequest request,
            @PathVariable("id") Long id, BookingFilter filter
//            @RequestParam(name = "date")
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateStart, // ISO 8601 format
//            @Valid @Max(7) @Min(1) Integer duration  // ISO 8601 format
    ) {
        filter.setOrgaId(id);

        Page response = bookingService.getAllCourtBookings(filter);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }

    @Operation(summary = "Place Court Booking REST API")
    @PostMapping("/bookings")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<ApiResponse<?>> createBooking(
            HttpServletRequest request,
            @Valid @RequestBody PlaceBookingPayload payload
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long accountId = ((CustomUserDetails) auth.getPrincipal()).getId();

        payload.setAccountId(accountId);

        BookingResponse response = bookingService.createBooking(payload);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success", "", request.getRequestURI(), response));
    }

}
