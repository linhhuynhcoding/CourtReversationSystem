package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "revenue")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "orga_id")
    private Organisation organisation;
    
    @Column
    private LocalDateTime date;
    
    @Column
    private Double totalRevenue;

    @Column
    private Integer totalBookings;
    
    @Column
    private String city;
}
