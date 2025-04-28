package com.app.CourtReservationSystem.model;

import com.app.CourtReservationSystem.model.compositeKeys.BookingRevenueId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_revenue_stats")
@Getter @Setter
@IdClass(BookingRevenueId.class)
public class BookingRevenueStats {

    @ManyToOne()
    @JoinColumn(name = "orga_id")
    @Id
    private Organisation organisation;

    @Column
    @Id
    private LocalDateTime date;

    @Column(name = "total_bookings")
    private Long totalBookings;

    @Column(name = "total_revenue")
    private Double totalRevenue;

    @Column
    private String city;

}
