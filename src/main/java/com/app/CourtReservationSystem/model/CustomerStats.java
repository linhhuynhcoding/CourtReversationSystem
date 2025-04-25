package com.app.CourtReservationSystem.model;

import com.app.CourtReservationSystem.model.relationships.ManagerAccount;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer_stats")
public class CustomerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "orga_id")
    private Organisation organisation;
    
    @Column(name = "new_customers")
    private Integer newCustomers;
    
    @Column(name = "returning_customers")
    private Integer returningCustomers;
    
    @Column
    private String city;
    
    @Column
    private LocalDateTime date;
}
