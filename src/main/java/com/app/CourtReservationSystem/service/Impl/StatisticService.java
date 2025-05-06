package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.noti.NotiPayload;
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
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class StatisticService implements IStatisticService {
    
    Logger logger = LoggerFactory.getLogger(StatisticService.class);
    
    AccountRepository accountRepository;
    OrgaRepository organizationRepository;
    BookingRepository bookingRepository;
    RoleRepository roleRepository;
    BookingRevenueStatsRepository bookingRevenueStatsRepository;
    CustomerStatsRepository customerStatsRepository;
    BookingRevenueStatsRepository revenueRepository;
    UsageStatsRepository usageStatsRepository;
    
    @Override
    public SystemStatisticResponse getSystemStatistic() {
        var today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        
        var fromLocalDate = today.minusHours(7);
        var toLocalDate = today.plusHours(17);
        
        return getSystemStatistic(fromLocalDate, toLocalDate);
    }
    
    @Override
    public SystemStatisticResponse getSystemStatistic(Integer days) {
        days -= 1;
        
        var today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        
        var fromLocalDate = today.minusHours(7).minusDays(days);
        var toLocalDate = today.plusHours(17);
        
        return getSystemStatistic(fromLocalDate, toLocalDate);
    }
    
    @Override
    public SystemStatisticResponse getSystemStatistic(LocalDateTime fromDate, LocalDateTime toDate) {
        SystemStatisticResponse response;
        
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
        
        
        response = SystemStatisticResponse.builder()
            .totalUsers(totalUsers)
            .totalOrganisations(totalOrganisations)
            .totalOrganisationsOpening(totalOrganisationsOpening)
            .totalOrganisationsMaintaining(totalOrganisationsMaintaining)
            .totalOrganisationsClosed(totalOrganisationsClosed)
            .totalRevenueBooking(totalRevenueBooking)
            .bookingTimes(bookingTimes)
            .revenueBookingLocations(revenueBookingLocations)
            .build();
        
        return response;
    }
    
    // TODO: refactor date
    @Override
    @Transactional
    public void updateStatistic() {
        var today = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        
        var fromLocalDate = today;
        var toLocalDate = today.plusDays(1);
        
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
//            System.out.println(revenueQueryResponse);
            BookingRevenueStats bookingRevenueStats1 =
                bookingRevenueStatsRepository.getBookingRevenue(revenueQueryResponse.getOrganisation(), today)
                .orElse(null);
            
            if (bookingRevenueStats1 == null) {
                bookingRevenueStats1 = new BookingRevenueStats();
                bookingRevenueStats1.setOrganisation(revenueQueryResponse.getOrganisation());
                bookingRevenueStats1.setDate(today);
            }
            
            bookingRevenueStats1.setCity(revenueQueryResponse.getOrganisation().getAddress().getCity());
            bookingRevenueStats1.setTotalBookings(revenueQueryResponse.getTotalBookings());
            bookingRevenueStats1.setTotalRevenue(revenueQueryResponse.getTotalRevenue());
            
            bookingRevenueStats.add(bookingRevenueStats1);
        }
        
        bookingRevenueStatsRepository.saveAll(bookingRevenueStats);
    }
}
