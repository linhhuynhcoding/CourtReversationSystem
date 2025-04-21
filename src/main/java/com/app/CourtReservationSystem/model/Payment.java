package com.app.CourtReservationSystem.model;
/**
 * @author linhhuynhcoding
 */

import com.app.CourtReservationSystem.enums.PaymentMethod;
import com.app.CourtReservationSystem.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "payments")
@Audited
@Data
public class Payment extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "amount")
  private Double amount;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private PaymentStatus status = PaymentStatus.PENDING;

  @Column(name="method_id")
  private PaymentMethod methodPayment;
  
  @OneToOne(mappedBy = "payment")
  @JsonIgnore
  private Booking booking;

  @OneToOne(mappedBy = "payment")
  @JsonIgnore
  private Order order;
}

