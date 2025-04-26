package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "support_channels")
@Getter @Setter
public class SupportChannel extends Audiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "channel")
    private List<SupportRequest> requests;

}


