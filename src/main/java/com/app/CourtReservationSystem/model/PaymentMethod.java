package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "payment_methods")
@Audited
@Data
public class PaymentMethod extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;
}

