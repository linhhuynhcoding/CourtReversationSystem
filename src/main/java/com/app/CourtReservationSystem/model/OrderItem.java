package com.app.CourtReservationSystem.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * @author linhhuynhcoding
 *
 */
@Entity
@Table(name = "order_items")
@Data
public class OrderItem extends Audiable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @Column(name = "unit_price", nullable = false)
  private Double unitPrice;

  @Column(name = "total_price", nullable = false)
  private Double totalPrice;
}