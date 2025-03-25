package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import java.util.List;

/**
 * @author linhhuynhcoding
 *
 */
@Entity
@Table(name = "products")
@Audited
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private Long buyTurn;

  @Column(nullable = false)
  private Long stock;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  
  @OneToMany(mappedBy = "product")
  private List<CartItem> cartItems;
  
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "image_id")
  private Image imageProduct;
}
