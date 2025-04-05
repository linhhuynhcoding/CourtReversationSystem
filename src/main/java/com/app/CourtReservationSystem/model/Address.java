package com.app.CourtReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "addresses")
@Audited
@Data
@EqualsAndHashCode(callSuper = true)
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
  private Long latitude;

  @Column(name = "longitude")
  private Long longitude;
  
  @JsonIgnore
  @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
  private Organisation court;
}
