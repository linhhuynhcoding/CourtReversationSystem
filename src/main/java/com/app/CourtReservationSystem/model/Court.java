package com.app.CourtReservationSystem.model;
/**
 * @author linhhuynhcoding
 */

import com.app.CourtReservationSystem.enums.CourtStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "courts")
@Audited
@Data
public class Court extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "phone")
  private String phone;

  @Column(name = "number_of_courts")
  private Integer numberOfCourts;

  @Column(name = "price")
  private Double price;

  @Column(name = "address_id")
  private Long addressId;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private CourtStatus status;
}