package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.noti.NotiPayload;
import com.app.CourtReservationSystem.dto.noti.NotiResponse;
import com.app.CourtReservationSystem.enums.SenderType;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.NotiMapper;
import com.app.CourtReservationSystem.model.Notification;
import com.app.CourtReservationSystem.model.Organisation;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.repository.NotificationRepository;
import com.app.CourtReservationSystem.service.INotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationService implements INotificationService {
    NotificationRepository notiRepository;
    AccountRepository accountRepository;
    NotiMapper notiMapper;

    @Override
    public List<NotiResponse> getUserNoti(Long accountId) {
        var account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Accouht", "id", accountId));

        List<Notification> notis = notiRepository.findUserNotifications(account);

        return notiMapper.toDTOs(notis);
    }

    @Override
    public void addNoti(NotiPayload noti) {
        var type = noti.getSenderType();
        var notification = notiMapper.toEntity(noti);

        if (noti.getRecipientId() != null) {
            var recipient = accountRepository.findById(noti.getRecipientId()).orElseThrow(() -> new ResourceNotFoundException("Recipient", "id", noti.getRecipientId()));
            notification.setRecipient(recipient);
        }
        notification.setSentTime(LocalDateTime.now());

        notiRepository.save(notification);
    }

}
