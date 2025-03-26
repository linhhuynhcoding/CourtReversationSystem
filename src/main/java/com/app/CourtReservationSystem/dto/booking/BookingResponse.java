package com.app.CourtReservationSystem.dto.booking;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.dto.court.CourtResponse;
import com.app.CourtReservationSystem.enums.BookingStatus;
import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.model.Payment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class BookingResponse {
    private Long id;
    
    private Long orderId;
    
    private CourtResponse court;

    private AccountResponse account;
    
    private Date timeStart;
    
    private Date timeEnd;
    
    private BookingStatus status;
    
    private Payment payment;
}
