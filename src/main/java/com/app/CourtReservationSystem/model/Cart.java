package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * @author linhhuynhcoding
 *
 */
@Entity
@Table(name = "carts")
public class Cart extends Audiable {
    @Id
    @GeneratedValue()
    @Getter @Setter
    private int id;

    @OneToOne @MapsId
    @Getter @Setter
    private Account account;

    @OneToMany(mappedBy = "cart")
    @Getter @Setter
    private List<CartItem> items;

}
