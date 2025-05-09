package com.app.CourtReservationSystem.service;

import com.app.CourtReservationSystem.dto.statistic.SystemStatisticResponse;

import java.time.LocalDateTime;

public interface IStatisticService {
    SystemStatisticResponse getSystemStatistic();

    SystemStatisticResponse getSystemStatistic(Integer days);

    SystemStatisticResponse getSystemStatistic(LocalDateTime fromDate, LocalDateTime toDate);

    void updateStatistic();
}
