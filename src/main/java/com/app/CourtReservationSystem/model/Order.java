package com.app.CourtReservationSystem.model;

import com.app.CourtReservationSystem.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.List;

/**
 * @author linhhuynhcoding
 */
@Entity
@Table(name = "orders")
@Audited
@Data
public class Order extends Audiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "order_type")
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column(name = "total")
    private Double total;

    @Column(name = "product_price")
    private Double productPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", nullable = true)
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    @JsonIgnore
    private Booking booking;

    @Column(name = "ship_fee")
    private Double shipFee;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}