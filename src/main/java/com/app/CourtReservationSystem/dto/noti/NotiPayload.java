package com.app.CourtReservationSystem.dto.noti;

import com.app.CourtReservationSystem.dto.account.AccountResponse;
import com.app.CourtReservationSystem.enums.NotificationType;
import com.app.CourtReservationSystem.enums.RecipientType;
import com.app.CourtReservationSystem.enums.SenderType;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotiPayload {
    private String title;
    private String message;
    private Long senderId;
    private SenderType senderType;
    private Long recipientId;
    private NotificationType notiType = NotificationType.BOOKING;
    private String role;
    private RecipientType recipientType = RecipientType.INDIVIDUAL;
}
