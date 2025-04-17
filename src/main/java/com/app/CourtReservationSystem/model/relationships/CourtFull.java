package com.app.CourtReservationSystem.model.relationships;

import com.app.CourtReservationSystem.model.Organisation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Entity
@Table(name = "court_status")
@Setter
@Getter
@Audited
public class CourtFull {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orga_id")
    private Organisation organisation;

    private LocalDateTime date;
}
