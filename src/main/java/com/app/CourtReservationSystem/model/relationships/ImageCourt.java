package com.app.CourtReservationSystem.model.relationships;

import com.app.CourtReservationSystem.model.Audiable;
import com.app.CourtReservationSystem.model.Organisation;
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
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;
    
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "court_id")
    private Organisation courtImage;
}
