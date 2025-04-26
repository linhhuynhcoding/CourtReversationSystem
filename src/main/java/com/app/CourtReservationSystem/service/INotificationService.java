package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.noti.NotiPayload;
import com.app.CourtReservationSystem.dto.noti.NotiResponse;

import java.util.List;

public interface INotificationService {
    public List<NotiResponse> getUserNoti(Long accountId);
    void addNoti(NotiPayload noti);
//    void addRemindBookingNoti(Long accountId);

    // TODO: viết contructor (ở lớp dẫn xuất)
}
