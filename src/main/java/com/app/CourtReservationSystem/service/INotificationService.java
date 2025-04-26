package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.noti.NotiPayload;
import com.app.CourtReservationSystem.dto.noti.NotiResponse;
import com.app.CourtReservationSystem.model.Account;

import java.util.List;

public interface INotificationService {
    public List<NotiResponse> getUserNoti(Long accountId);
    void addNoti(NotiPayload noti);
    void addRemindBookingNoti(Account account);

    // TODO: viết contructor (ở lớp dẫn xuất)
}
