package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;

@Entity
@Table
public class Carts {
    @Id
    @GeneratedValue()
    private int id;

    @Column
    private int account_id;

    @Column
    private int items

}
