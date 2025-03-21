package com.app.CourtReservationSystem.model;
import com.app.CourtReservationSystem.enums.OrderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.List;

/**
 * @author linhhuynhcoding
 *
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

  @Column(name = "account_id")
  private Long accountId;

  @Column(name = "order_type")
  @Enumerated(EnumType.STRING)
  private OrderType orderType;

  @Column(name = "total")
  private Integer total;

  @Column(name = "payment_id")
  private Long paymentId;

  @Column(name = "address_id")
  private Long addressId;

  @Column(name = "court_id")
  private Long courtId;

  @Column(name = "ship_fee")
  private Double shipFee;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems;
}