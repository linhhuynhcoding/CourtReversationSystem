package com.app.CourtReservationSystem.dto.statistic;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class RevenueBookingLocation {
    private Double totalRevenue; //Tong doanh thu cua tat ca
    private Long bookingTimes; //So tong booking
    private String location;

    public RevenueBookingLocation(Double totalRevenue, Long bookingTimes, String location) {
        this.totalRevenue = totalRevenue;
        this.bookingTimes = bookingTimes;
        this.location = location;
    }
}
