package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.List;

/**
 * @author linhhuynhcoding
 */
@Entity
@Table(name = "carts")
@Audited
@Getter @Setter
public class Cart extends Audiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
//    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;
    
    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;

    @Column
    private Double totalPrice;
}
