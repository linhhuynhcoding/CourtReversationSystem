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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false, updatable = false)
    private Cart cart;

    @ManyToOne()
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @Column
    private Double price;

    @Column
    private Integer quantity;
    
    @Column
    private boolean selected = false;
}

