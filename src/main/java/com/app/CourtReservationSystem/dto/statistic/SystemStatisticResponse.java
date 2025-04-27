package com.app.CourtReservationSystem.dto.statistic;

import com.app.CourtReservationSystem.dto.product.ProductResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter @Getter
public class SystemStatisticResponse {
    private Integer totalUsers;
    private Integer totalOrganisations;
    private Integer totalOrganisationsOpening;
    private Integer totalOrganisationsMaintaining;
    private Integer totalOrganisationsClosed;
    private Double totalRevenueBooking; //Tong doanh thu cua tat ca booking (Theo thời gian)
    private Integer bookingTimes; //So tong booking (Theo thời gian)
    private List<RevenueBookingLocation> revenueBookingLocations;
    private Double totalRevenueOrder; //Tong doanh thu don hang
    private Integer successOrderCount;
    private Integer processingOrderCount;
    private Integer failedOrderCount;
    private List<ProductResponse> topProduct;
}
