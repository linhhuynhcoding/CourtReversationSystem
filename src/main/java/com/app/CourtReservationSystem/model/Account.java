package com.app.CourtReservationSystem.model;

import com.app.CourtReservationSystem.model.relationships.ManagerAccount;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.Manager;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.List;

/**
 * @author linhhuynhcoding
 *
 */
@Entity
@Table(name = "accounts", indexes = @Index(name = "index_accounts_username", columnList = "username", unique = true))
@Audited
@Getter @Setter
public class Account extends Audiable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, updatable = true)
    private Role accountRole;
    
    @OneToMany(mappedBy = "account")
    private List<Booking> bookings;

    @OneToMany(mappedBy = "account")
    private List<Order> orders;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private ManagerAccount manager;

}
