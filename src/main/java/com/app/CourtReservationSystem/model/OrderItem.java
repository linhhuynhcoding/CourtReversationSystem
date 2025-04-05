package com.app.CourtReservationSystem.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.List;

/**
 * @author linhhuynhcoding
 *
 */
@Entity
@Table(name = "order_items")
@Audited
@Data
public class OrderItem extends Audiable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @Column(name = "unit_price", nullable = false)
  private Double unitPrice;

  @Column(name = "total_price", nullable = false)
  private Double totalPrice;
}