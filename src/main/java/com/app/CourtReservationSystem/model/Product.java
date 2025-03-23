package com.app.CourtReservationSystem.model;

import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import com.app.CourtReservationSystem.model.relationships.ImageProduct;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
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
public class Product extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private Long quantity;

  @Column(nullable = false)
  private Long stock;

  @Column(nullable = false)
  private String image;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  
  @OneToMany(mappedBy = "product")
  private List<CartItem> cartItems;
  
  @OneToMany(mappedBy = "productImage")
  private List<ImageProduct> imageProducts;
}
