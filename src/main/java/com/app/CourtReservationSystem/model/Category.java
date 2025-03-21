package com.app.CourtReservationSystem.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

/**
 * @author linhhuynhcoding
 *
 */
@Entity
@Table(name = "categories")
@Audited
@Data
public class Category extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;
}
