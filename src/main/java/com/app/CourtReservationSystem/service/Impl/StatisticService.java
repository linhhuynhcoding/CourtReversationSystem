package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.dto.statistic.BookingRevenueQueryResponse;
import com.app.CourtReservationSystem.dto.statistic.RevenueBookingLocation;
import com.app.CourtReservationSystem.dto.statistic.SystemStatisticResponse;
import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.model.BookingRevenueStats;
import com.app.CourtReservationSystem.model.Role;
import com.app.CourtReservationSystem.repository.*;
import com.app.CourtReservationSystem.service.IStatisticService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatisticService implements IStatisticService {

    AccountRepository accountRepository;
    OrgaRepository organizationRepository;
    BookingRepository bookingRepository;
    RoleRepository roleRepository;
    BookingRevenueStatsRepository bookingRevenueStatsRepository;
    CustomerStatsRepository customerStatsRepository;
    RevenueRepository revenueRepository;
    UsageStatsRepository usageStatsRepository;

    @Override
    public SystemStatisticResponse getSystemStatistic() {
        return null;
    }

    @Override
    public SystemStatisticResponse getSystemStatistic(LocalDateTime fromDate, LocalDateTime toDate) {
        SystemStatisticResponse response = new SystemStatisticResponse();

        // Define variables
        Long totalUsers;
        Long totalOrganisations;
        Long totalOrganisationsOpening;
        Long totalOrganisationsMaintaining;
        Long totalOrganisationsClosed;
        Double totalRevenueBooking; //Tong doanh thu cua tat ca booking
        Long bookingTimes; //So tong booking
        List<RevenueBookingLocation> revenueBookingLocations;
        Double totalRevenueOrder; //Tong doanh thu don hang
        Long successOrderCount;
        Long processingOrderCount;
        Long failedOrderCount;
        List<ProductResponse> topProduct;

        // TODO: Update role
        Role role = roleRepository.findByRole("PLAYER");
        totalUsers = accountRepository.countByAccountRole(role);

        totalOrganisations = organizationRepository.count();
        totalOrganisationsOpening = organizationRepository.countByStatus(CourtStatus.OPENING);
        totalOrganisationsMaintaining = organizationRepository.countByStatus(CourtStatus.MAINTAINABLE);
        totalOrganisationsClosed = organizationRepository.countByStatus(CourtStatus.CLOSED);

        totalRevenueBooking = revenueRepository.getTotalRevenueBooking(fromDate, toDate);
        bookingTimes = bookingRepository.countBookingByTimeStartBetween(fromDate, toDate);
        revenueBookingLocations = revenueRepository.getRevenueGroupByCity(fromDate, toDate);


        response.setTotalUsers(totalUsers);
        response.setTotalOrganisations(totalOrganisations);
        response.setTotalOrganisationsOpening(totalOrganisationsOpening);
        response.setTotalOrganisationsMaintaining(totalOrganisationsMaintaining);
        response.setTotalOrganisationsClosed(totalOrganisationsClosed);
        response.setTotalRevenueBooking(totalRevenueBooking);
        response.setBookingTimes(bookingTimes);
        response.setRevenueBookingLocations(revenueBookingLocations);

        return response;
    }

    // TODO: refactor date
    @Override
    @Transactional
    public void updateStatistic() {
        var today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);

        var fromLocalDate = today.minusHours(7);
        var toLocalDate = today.plusHours(17);

        System.out.println("[today]: " + today);
        System.out.println("[fromLocalDate]: " + fromLocalDate);
        System.out.println("[toLocalDate]: " + toLocalDate);


        updateBookingRevenueStats(today, fromLocalDate, toLocalDate);

    }

    @Transactional
    public void updateBookingRevenueStats(LocalDateTime today, LocalDateTime fromLocalDate, LocalDateTime toLocalDate) {
        List<BookingRevenueStats> bookingRevenueStats = new ArrayList<>();
        List<BookingRevenueQueryResponse> revenue = bookingRepository.getBookingRevenue(fromLocalDate, toLocalDate);

        for (BookingRevenueQueryResponse revenueQueryResponse : revenue) {
            System.out.println(revenueQueryResponse);
            BookingRevenueStats bookingRevenueStats1 = bookingRevenueStatsRepository.getBookingRevenue(revenueQueryResponse.getOrganisation(), today)
                    .orElse(null);

            if (bookingRevenueStats1 == null) {
                bookingRevenueStats1 = new BookingRevenueStats();
                bookingRevenueStats1.setOrganisation(revenueQueryResponse.getOrganisation());
                bookingRevenueStats1.setDate(today);
            }

            bookingRevenueStats1.setTotalBookings(revenueQueryResponse.getTotalBookings());
            bookingRevenueStats1.setTotalRevenue(revenueQueryResponse.getTotalRevenue());

            bookingRevenueStats.add(bookingRevenueStats1);
        }

        bookingRevenueStatsRepository.saveAll(bookingRevenueStats);
    }
}
