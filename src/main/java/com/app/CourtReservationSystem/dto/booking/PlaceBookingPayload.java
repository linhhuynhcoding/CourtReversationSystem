package com.app.CourtReservationSystem.dto.booking;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter @Setter
public class PlaceBookingPayload {
    private Long accountId;

    @NotNull
    private Long orgaId;

    @NotNull
    private Long courtId;
    
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeStart;
    
    @NotNull
    @DecimalMin("0.5")
    private double duration = 1.0;
}
