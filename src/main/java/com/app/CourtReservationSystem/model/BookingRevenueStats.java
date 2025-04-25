package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_revenue_stats")
public class BookingRevenueStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "orga_id")
    private Organisation organisation;
    
    @Column(name = "total_bookings")
    private Double totalBookings;
    
    @Column(name = "total_revenue")
    private Double totalRevenue;
    
    @Column
    private LocalDateTime date;
}
