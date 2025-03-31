package com.app.CourtReservationSystem.model;

import com.app.CourtReservationSystem.enums.ResolutionType;
import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name="images")
@Getter @Setter
public class Image extends Audiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(nullable = false)
    private String image_url;
    
    @Column(nullable = false)
    private Integer width;
    
    @Column(nullable = false)
    private Integer height;
    
    @Column(nullable = false)
    private ResolutionType type = ResolutionType.ORIGINAL;
    
    @OneToOne(mappedBy = "images")
    private ImageCourt imageCourt;

    @OneToOne(mappedBy = "imageProduct")
    private Product product;

}
