package com.app.CourtReservationSystem.dto.statistic;

import com.app.CourtReservationSystem.model.Organisation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRevenueQueryResponse {
    private Organisation organisation;

    private Long totalBookings;

    private Double totalRevenue;

    public BookingRevenueQueryResponse(
            Organisation organisation,
            Long totalBookings,
            Double totalRevenue
    ) {
        this.organisation = organisation;
        this.totalBookings = totalBookings;
        this.totalRevenue = totalRevenue;
    }

    @Override
    public String toString() {
        return "[BookingRevenueQueryResponse] = " + this.organisation.getId() + ", " + this.organisation.getName() + ", " + this.totalBookings + ", " + this.totalRevenue;
    }
}
