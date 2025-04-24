package com.app.CourtReservationSystem.dto.noti;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.enums.NotificationType;
import com.app.CourtReservationSystem.enums.RecipientType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class NotiPayload {
    private String title;
    private String message;
    private Long recipientId;
    private NotificationType notiType;
    private String role;
    private RecipientType recipientType;
}
