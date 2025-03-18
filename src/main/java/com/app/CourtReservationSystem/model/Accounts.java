package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts", indexes = @Index(name = "index_accounts_username", columnList = "username", unique = true))
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private int role_id;


}
