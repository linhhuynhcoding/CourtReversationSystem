package com.app.CourtReservationSystem.model.relationships;

import com.app.CourtReservationSystem.model.Audiable;
import com.app.CourtReservationSystem.model.Court;
import com.app.CourtReservationSystem.model.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Setter @Getter
@Table(name = "image_courts")
public class ImageCourt extends Audiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @OneToOne()
    @JoinColumn(name = "image_id")
    private Image image;
    
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "court_id")
    private Court courtImage;
}
