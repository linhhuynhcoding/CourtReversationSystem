package com.app.CourtReservationSystem.model;

import com.app.CourtReservationSystem.enums.ResolutionType;
import com.app.CourtReservationSystem.model.relationships.ImageCourt;
import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name="images")
public class Image extends Audiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(nullable = false)
    private String image_url;
    
    @Column(nullable = false)
    private Long width;
    
    @Column(nullable = false)
    private Long height;
    
    @Column(nullable = false)
    private ResolutionType type;
    
    @OneToOne(mappedBy = "images")
    private ImageCourt imageCourt;
}
