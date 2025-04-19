package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.order.OrderFilter;
import com.app.CourtReservationSystem.dto.order.OrderResponse;
import com.app.CourtReservationSystem.dto.order.PlaceOrderBookingPayload;
import com.app.CourtReservationSystem.dto.order.PlaceOrderPayload;
import com.app.CourtReservationSystem.security.CustomUserDetails;
import com.app.CourtReservationSystem.service.IOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
        name = "order"
)
public class OrderController {
    IOrderService orderService;

    @GetMapping("/orders/{id}")
    public ResponseEntity<ApiResponse<?>> getOrder(
            HttpServletRequest request,
            @PathVariable(name = "id") Long id) {
        OrderResponse response = orderService.getOrder(id);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), response));
    }

    @GetMapping("/accounts/{id}/orders")
    public ResponseEntity<ApiResponse<?>> getAccountOrder(
            HttpServletRequest request,
            @PathVariable(name = "id") Long id,
            @Valid OrderFilter orderFilter
    ) {
        Pageable pageable = PageRequest.of(orderFilter.getPage(), orderFilter.getPageSize());
        Page response = orderService.getAccountOrders(id, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), response));
    }

    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<?>> getAllOrder(
            HttpServletRequest request,
            @Valid OrderFilter orderFilter
    ) {
        Pageable pageable = PageRequest.of(orderFilter.getPage(), orderFilter.getPageSize());
        Page response = orderService.getOrders(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), response));
    }

    @PostMapping("/orders")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<ApiResponse<?>> createOrder(
            HttpServletRequest request,
            @Valid @RequestBody PlaceOrderPayload placeOrderPayload
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        OrderResponse response = orderService.placeOrder(userDetails.getId(), placeOrderPayload);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), response));
    }
    
    @PostMapping("/orders/booking")
    @SecurityRequirement(name = "Bear Authentication")
    public ResponseEntity<ApiResponse<?>> createOrderBooking(
        HttpServletRequest request,
        @Valid @RequestBody PlaceOrderBookingPayload placeOrderPayload
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        
        OrderResponse response = orderService.placeOrderBooking(userDetails.getId(), placeOrderPayload);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Success!", "",
            request.getRequestURI(), response));
    }
}
