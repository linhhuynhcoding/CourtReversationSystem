package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.service.IStatisticService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CronJobService {

    @Autowired
    IStatisticService statisticService;

    @Autowired
    TelegramService telegramService;

    @Scheduled(fixedRate = 5000)
    void updateStatisticEveryHour(){
        System.out.println(LocalDateTime.now() + " ----CRON JOB SERVICE: updateStatisticEveryHour: CHẠY MỖI 1 GIỜ!----");
        statisticService.updateStatistic();
    }

    @Scheduled(cron = "0 58 23 * * ?")
    void clearDataBookingFull(){
        System.out.println(LocalDateTime.now() + " ----CRON JOB SERVICE: clearDataBookingFull: CHẠY MỖI TỐI!----");
        // TODO: gọi service xóa data dư thừa
    }

    @Scheduled(cron = "0 40 13 * * ?")
    void sentMessageTelegramEveryDay(){
        System.out.println(LocalDateTime.now() + " ----CRON JOB SERVICE: sentMessageTelegramEveryDay: CHẠY MỖI TỐI!----");
        telegramService.sentRevenueReport();
    }


}
