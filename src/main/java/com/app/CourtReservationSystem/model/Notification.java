package com.app.CourtReservationSystem.model;

import com.app.CourtReservationSystem.enums.NotificationType;
import com.app.CourtReservationSystem.enums.RecipientType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Entity
@Audited
@Setter @Getter
public class Notification extends Audiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String message;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = true)
    private Account recipient;

    @Column
    private String notiType;
    @Column
    private LocalDateTime sentTime;
    @Column
    private String role;
    @Column
    private String recipientType;
    @Column(nullable = true)
    private boolean isSeen = false;
}
