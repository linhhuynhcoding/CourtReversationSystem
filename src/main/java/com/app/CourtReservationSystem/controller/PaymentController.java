package com.app.CourtReservationSystem.controller;

import com.app.CourtReservationSystem.dto.ApiResponse;
import com.app.CourtReservationSystem.dto.court.CreateCourtPayload;
import com.app.CourtReservationSystem.dto.court.OrgaResponse;
import com.app.CourtReservationSystem.dto.payment.PaymentPayload;
import com.app.CourtReservationSystem.service.IPaymentMethodService;
import com.app.CourtReservationSystem.service.IPaymentService;
import com.app.CourtReservationSystem.service.Impl.VNPAYService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        var responsePayment = paymentService.handlePaymentBooking(request, payload);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Success!", "",
                request.getRequestURI(), responsePayment));
    }
}
