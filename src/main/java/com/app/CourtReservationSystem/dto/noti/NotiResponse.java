package com.app.CourtReservationSystem.dto.noti;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.enums.NotificationType;
import com.app.CourtReservationSystem.enums.RecipientType;
import com.app.CourtReservationSystem.model.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotiResponse {
    private Long id;
    private String title;
    private String message;
    private AccountResponse sender;
    private AccountResponse recipient;
    private NotificationType notiType;
    private LocalDateTime sentTime;
    private String role;
    private RecipientType recipientType;
    private boolean isSeen;
}
