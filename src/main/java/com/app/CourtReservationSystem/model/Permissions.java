package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;

@Entity
@Table
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String code;
}
