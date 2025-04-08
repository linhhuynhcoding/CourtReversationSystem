package com.app.CourtReservationSystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "support_messages")
@Getter
@Setter
public class SupportMessage extends Audiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderType; // "user" or "agent"

    private Long senderId;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private SupportRequest request;

    @Column(columnDefinition = "TEXT")
    private String message;

}
