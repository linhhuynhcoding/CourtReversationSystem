package com.app.CourtReservationSystem.model;
/**
 * @author linhhuynhcoding
 */

import com.app.CourtReservationSystem.enums.CourtStatus;
import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import com.app.CourtReservationSystem.model.relationships.ManagerAccount;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.List;

@Entity
@Table(name = "organisations")
@Audited
@Data
@EqualsAndHashCode(callSuper = true)
public class Organisation extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "phone")
  private String phone;

  @Column(name = "number_of_courts")
  private Long numberOfCourts;

  @Column(name = "price")
  private Double price;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private CourtStatus status;
  
  @OneToMany(mappedBy = "courtImage", cascade = CascadeType.ALL)
  private List<ImageCourt> imageCourts;

  @OneToMany(mappedBy = "organisation", cascade = CascadeType.ALL)
  private List<Court> courts;

  @OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
  private List<ManagerAccount> manager;
}