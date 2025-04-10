package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.OrgaResponse;
import com.app.CourtReservationSystem.dto.payment.PaymentPayload;
import com.app.CourtReservationSystem.service.IPaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(
        name = "payment"
)
public class PaymentController {
    IPaymentService paymentService;

    @PostMapping("/bookings")
    public ResponseEntity<ApiResponse<?>> purchaseBooking (
            HttpServletRequest request,
            HttpServletResponse response,
            @Valid @RequestBody PaymentPayload payload
            ) throws IOException {
        var responsePayment = paymentService.handlePaymentBooking(payload);

        // Thực hiện redirect nếu có
        if (responsePayment.isRedirect()) {
            response.sendRedirect(responsePayment.getRedirectUrl());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), responsePayment));
    }
}
