package com.app.CourtReservationSystem.dto.statistic;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class RevenueBookingLocation {
    private Double totalRevenue; //Tong doanh thu cua tat ca
    private Integer bookingTimes; //So tong booking
    private String location;
}
