package com.app.CourtReservationSystem.model.relationships;

import com.app.CourtReservationSystem.model.Audiable;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.model.Image;
import com.app.CourtReservationSystem.model.Product;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "image_products")
public class ImageProduct extends Audiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @OneToOne()
    @JoinColumn(name = "image_id")
    private Image images;
    
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product productImage;
}
