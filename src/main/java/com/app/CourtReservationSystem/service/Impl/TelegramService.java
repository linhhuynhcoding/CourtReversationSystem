package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.statistic.SystemStatisticResponse;
import com.app.CourtReservationSystem.service.IStatisticService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class TelegramService {

    @Value("${spring.telegram.token}")
    private String botFatherToken;
    //
    @Value("${spring.telegran.chatid}")
    private String chatId;

    @Autowired
    IStatisticService statisticService;

    void sentMessage(String message) {
        System.out.println("=== Telegram Service === senting message...");

        var jsonObject = new JSONObject();
        jsonObject.put("text", message);
        jsonObject.put("chat_id", chatId);
        jsonObject.put("parse_mode", "html");

        System.out.println(jsonObject);

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request =
                new HttpEntity<String>(jsonObject.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForObject("https://api.telegram.org/bot" + botFatherToken + "/sendMessage", request, String.class);
    }

    void sentRevenueReport() {
        SystemStatisticResponse response = statisticService.getSystemStatistic();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedRevenue = currencyFormat.format(response.getTotalRevenueBooking());

        String message = "";
        message += "\uD83D\uDFE2\t<strong>Thống kê doanh thu cuối ngày</strong>\t\uD83D\uDFE2\n\n";
        message += "✅ Tổng doanh thu: \t<b>" + formattedRevenue + "</b>\n";
        message += "✅ Tổng số lượng booking: \t<b>" + response.getBookingTimes() + "</b>\n\n";
        message += "\uD83D\uDDD3\uFE0F " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n";

        sentMessage(message);
    }

    class Body {
        String chatId;
        String text;

        Body(String chatId, String text) {
            this.chatId = chatId;
            this.text = text;
        }
    }
}
