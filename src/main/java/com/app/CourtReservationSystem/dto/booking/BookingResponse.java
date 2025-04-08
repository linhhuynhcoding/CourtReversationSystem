package com.app.CourtReservationSystem.dto.booking;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.court.CourtResponse;
import com.app.CourtReservationSystem.dto.court.OrgaResponse;
import com.app.CourtReservationSystem.enums.BookingStatus;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.model.Payment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingResponse {
    private Long id;
    private Long orderId;
    private OrgaResponse orga;
    private AccountResponse account;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private BookingStatus status;
    private Payment payment;
    private BookingStatus bookingStatus;
    private CourtResponse court;
}
