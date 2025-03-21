package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name= "cart_items")
@Audited
@Data
public class CartItem extends Audiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false, updatable = false)
    @Getter @Setter
    private Cart cart;

//    @ManyToOne()
//    @JoinColumn(name = "product_id", nullable = false, updatable = false)
//    @Getter @Setter
//    private Product product;

    @Column
    @Getter @Setter
    private int quantity;
}

