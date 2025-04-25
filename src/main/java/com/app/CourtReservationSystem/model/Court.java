package com.app.CourtReservationSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;

@Entity
@Data
@Audited
@Table(name = "courts")
public class Court extends Audiable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orga_id")
    @JsonIgnore // ignore when return to client
    Organisation organisation;
}
