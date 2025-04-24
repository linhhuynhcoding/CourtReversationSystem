package com.app.CourtReservationSystem.mapper;

import com.app.CourtReservationSystem.dto.noti.NotiPayload;
import com.app.CourtReservationSystem.dto.noti.NotiResponse;
import com.app.CourtReservationSystem.model.Notification;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotiMapper {
    NotiResponse toDTO(Notification noti);
    List<NotiResponse> toDTOs(List<Notification> notiList);

    Notification toEntity(NotiPayload noti);
}
