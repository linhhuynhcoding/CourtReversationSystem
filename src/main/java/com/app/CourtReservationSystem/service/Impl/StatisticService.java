package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.dto.product.ProductResponse;
import com.app.CourtReservationSystem.dto.statistic.RevenueBookingLocation;
import com.app.CourtReservationSystem.dto.statistic.SystemStatisticResponse;
import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.model.Role;
import com.app.CourtReservationSystem.repository.*;
import com.app.CourtReservationSystem.service.IStatisticService;

import java.time.LocalDateTime;
import java.util.List;

public class StatisticService implements IStatisticService {

    AccountRepository accountRepository;
    OrgaRepository organizationRepository;
    BookingRepository bookingRepository;
    RoleRepository roleRepository;
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
        Integer totalUsers;
        Integer totalOrganisations;
        Integer totalOrganisationsOpening;
        Integer totalOrganisationsMaintaining;
        Integer totalOrganisationsClosed;
        Double totalRevenueBooking; //Tong doanh thu cua tat ca booking
        Integer bookingTimes; //So tong booking
        List<RevenueBookingLocation> revenueBookingLocations;
        Double totalRevenueOrder; //Tong doanh thu don hang
        Integer successOrderCount;
        Integer processingOrderCount;
        Integer failedOrderCount;
        List<ProductResponse> topProduct;

        // TODO: Update role
        Role role = roleRepository.findByRole("PLAYER");
        totalUsers = accountRepository.countByAccountRole(role);

        totalOrganisations = organizationRepository.countOrganizations();
        totalOrganisationsOpening = organizationRepository.countOrganizationsByStatus(CourtStatus.OPENING);
        totalOrganisationsMaintaining = organizationRepository.countOrganizationsByStatus(CourtStatus.MAINTAINABLE);
        totalOrganisationsClosed = organizationRepository.countOrganizationsByStatus(CourtStatus.CLOSED);

        totalRevenueBooking = revenueRepository.getTotalRevenueBooking(fromDate, toDate);
        bookingTimes = bookingRepository.countBookingByTimeStartBetween(fromDate, toDate);


        response.setTotalUsers(totalUsers);

        return null;
    }
}
