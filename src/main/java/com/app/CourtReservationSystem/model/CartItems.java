package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name= "cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private  int cart_id;

    @

}

