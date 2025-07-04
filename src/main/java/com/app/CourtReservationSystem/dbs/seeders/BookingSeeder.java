package com.app.CourtReservationSystem.dbs.seeders;

import com.app.CourtReservationSystem.model.Booking;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.model.Organisation;
import com.app.CourtReservationSystem.repository.AccountRepository;
import com.app.CourtReservationSystem.repository.BookingRepository;
import com.app.CourtReservationSystem.repository.CourtRepository;
import com.app.CourtReservationSystem.repository.OrgaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@Order(5)
public class BookingSeeder implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    OrgaRepository orgaRepository;

    @Autowired
    CourtRepository courtRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        System.out.println("BookingSeeder started");
//        loadBookingData();
    }

    @Transactional
    public void loadBookingData() {
        List<Booking> bookings = new ArrayList<>();

        for (long i = 1; i < 100; i++) {
            Booking booking = new Booking();
            Organisation orga = orgaRepository.findById(i).orElse(null);
            booking.setOrga(orga);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 7);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            if (orga != null && !orga.getCourts().isEmpty()) {
                booking.setCourt(orga.getCourts().get(0));
            }

            var timeStart = LocalDateTime.now().minusDays(i % 7).withHour((int) (14 + i % 3)).withMinute(0).withSecond(0).withNano(0);
            var timeEnd = timeStart.plusHours(i % 2);
//            var timeStart = OffsetDateTime.now().minusDays(i % 7).withHour((int) (14 + i % 3)).withMinute(0).withSecond(0).withNano(0);
//            var timeEnd = timeStart.plusHours(i % 2);

//            booking.setTimeStart(LocalDateTime.ofInstant(calendar.getTime().toInstant(), ZoneId.systemDefault()));
//            calendar.set(Calendar.HOUR_OF_DAY, (int) (Math.min(7 + i, 12)));
//            booking.setTimeEnd(LocalDateTime.ofInstant(calendar.getTime().toInstant(), ZoneId.systemDefault()));

            booking.setTimeStart(timeStart);
            booking.setTimeEnd(timeEnd);

            booking.setAccount(accountRepository.findById(i).orElse(null));
            booking.setTotal(orga.getPrice() * 2);
            booking.setReminded(true);

            bookings.add(booking);
        }


        bookingRepository.saveAll(bookings);
    }
}
