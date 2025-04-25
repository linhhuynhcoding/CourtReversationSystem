package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usage_stats")
public class UsageStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "orga_id")
    private Organisation organisation;
    
    @Column(name = "total_booking_times")
    private Double totalBookingTimes;
    
    @Column(name = "total_available_times")
    private Double totalAvailableTimes;
    
    @Column(name = "usage_rate")
    private Double usageRate;
    
    @Column
    private LocalDateTime date;
}
