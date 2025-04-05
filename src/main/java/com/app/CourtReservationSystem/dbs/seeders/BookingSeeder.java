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
        loadBookingData();
    }

    @Transactional
    public void loadBookingData() {
        List<Booking> bookings = new ArrayList<>();

        for (long i = 1; i < 100; i++) {
            Booking booking = new Booking();
            Organisation orga = orgaRepository.findById(i).orElse(null);
            booking.setOrga(orga);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 16);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            if (orga != null && !orga.getCourts().isEmpty()) {
                booking.setCourt(orga.getCourts().get(0));
            }


            booking.setTimeStart(calendar.getTime());
            calendar.set(Calendar.HOUR_OF_DAY, (int) (Math.min(16 + i, 23)));
            booking.setTimeEnd(calendar.getTime());
            booking.setAccount(accountRepository.findById(1L).orElse(null));

            bookings.add(booking);
        }

        bookingRepository.saveAll(bookings);
    }
}
