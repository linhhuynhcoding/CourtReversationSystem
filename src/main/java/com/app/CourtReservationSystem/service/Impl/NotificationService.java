package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.noti.NotiPayload;
import com.app.CourtReservationSystem.dto.noti.NotiResponse;
import com.app.CourtReservationSystem.enums.SenderType;
import com.app.CourtReservationSystem.exception.ResourceNotFoundException;
import com.app.CourtReservationSystem.mapper.NotiMapper;
import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.model.Booking;
import com.app.CourtReservationSystem.model.Notification;
import com.app.CourtReservationSystem.model.Organisation;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.repository.BookingRepository;
import com.app.CourtReservationSystem.repository.NotificationRepository;
import com.app.CourtReservationSystem.service.IBookingService;
import com.app.CourtReservationSystem.service.INotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationService implements INotificationService {
    NotificationRepository notiRepository;
    AccountRepository accountRepository;
    BookingRepository bookingRepository;
    NotiMapper notiMapper;


    @Override
    public List<NotiResponse> getUserNoti(Long accountId) {
        var account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Accouht", "id", accountId));

        List<Notification> notis = notiRepository.findUserNotifications(account);

        addRemindBookingNoti(account);

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



    @Override
    public void addRemindBookingNoti(Account account) {
        var accountId = account.getId();

        List<Booking> bookings = bookingRepository.findAllByAccountId(accountId);

        if (bookings.isEmpty()) {
            return;
        }

        bookings = bookings.stream().sorted((o1, o2) -> o1.getTimeStart().isBefore(o2.getTimeStart()) ? 1 : -1).toList();

        for (Booking booking : bookings) {

            var timeBooking = booking.getTimeStart().withMinute(0);
            var timeNow = LocalDateTime.now().plusHours(2).withMinute(0);

            if (timeBooking.isBefore(timeNow)) {
                if (booking.isReminded()) {
                    continue;
                }

                var orga = booking.getOrga();
                NotiPayload notiPayload = new NotiPayload();

                notiPayload.setTitle("Nhắc nhở sắp đến giờ booking");
                notiPayload.setMessage("<strong>" + orga.getName() + "</strong> đang chờ bạn đến nhận sân, đừng quên nhé!<br/>"
                        + "Mã đặt sân: " + booking.getId() + "<br/>"
                        + "Thời gian: " + booking.getTimeStart().plusHours(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "<br/>"
                );
                notiPayload.setSenderId(booking.getId());
                notiPayload.setSenderType(SenderType.ORGANISATION);
                notiPayload.setRecipientId(accountId);
                addNoti(notiPayload);

                booking.setReminded(true);
                bookingRepository.save(booking);

                return;
            }
        }
    }

}
