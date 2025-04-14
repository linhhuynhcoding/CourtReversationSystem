package com.app.CourtReservationSystem.model.relationships;

import com.app.CourtReservationSystem.model.Account;
import com.app.CourtReservationSystem.model.Image;
import com.app.CourtReservationSystem.model.Organisation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Setter
@Getter
@Table(name = "manager")
public class ManagerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "court_id")
    private Organisation court;
}
