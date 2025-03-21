package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Table(name = "roles")
@Data
@Audited
public class Role extends Audiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String role;

    @OneToMany(mappedBy = "role")
    private List<Account> accounts;

}
