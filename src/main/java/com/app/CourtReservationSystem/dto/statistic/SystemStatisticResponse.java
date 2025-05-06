package com.app.CourtReservationSystem.dto.statistic;

import com.app.CourtReservationSystem.dto.product.ProductResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter @Getter
@Builder
public class SystemStatisticResponse {
    private Long totalUsers;
    private Long totalOrganisations;
    private Long totalOrganisationsOpening;
    private Long totalOrganisationsMaintaining;
    private Long totalOrganisationsClosed;
    private Double totalRevenueBooking; //Tong doanh thu cua tat ca booking (Theo thời gian)
    private Long bookingTimes; //So tong booking (Theo thời gian)
    private List<RevenueBookingLocation> revenueBookingLocations;
    private Double totalRevenueOrder; //Tong doanh thu don hang
    private Long successOrderCount;
    private Long processingOrderCount;
    private Long failedOrderCount;
    private List<ProductResponse> topProduct;
}
