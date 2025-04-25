package com.app.CourtReservationSystem.dto.statistic;

import com.app.CourtReservationSystem.dto.court.OrgaResponse;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrganisationStatisticResponse {
    private OrgaResponse organisation;
    private Double totalRevenueBooking; //Tong doanh thu cua tat ca booking
    private Integer bookingTimes; //So tong booking
    private Integer returningCustomers;
    private Integer newCustomers;
    private Double totalBookingTimes; //thoi gian da booking
    private Double totalAvailableTimes; //thoi gian trong
    
}
