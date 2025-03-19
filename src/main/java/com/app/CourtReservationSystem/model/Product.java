package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

/**
 * @author linhhuynhcoding
 *
 */
@Entity
@Table(name = "products")
@Data
public class Product extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private Integer quantity;

  @Column(nullable = false)
  private Integer stock;

  @Column(nullable = false)
  private String image;

//  @ManyToOne
//  @JoinColumn(name = "category_id")
//  private Category category;
}
