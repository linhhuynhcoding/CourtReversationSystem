package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "addresses")
@Data
public class Address extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "city")
  private String city;

  @Column(name = "district")
  private String district;

  @Column(name = "ward")
  private String ward;

  @Column(name = "address_line")
  private String addressLine;

  @Column(name = "latitude")
  private Integer latitude;

  @Column(name = "longitude")
  private Integer longitude;
}
