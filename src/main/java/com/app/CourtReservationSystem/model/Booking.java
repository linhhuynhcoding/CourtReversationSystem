package com.app.CourtReservationSystem.model;
/**
 * @author linhhuynhcoding
 */

import com.app.CourtReservationSystem.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Table(name = "bookings")
@Audited
@Data
public class Booking extends Audiable implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id", nullable = true)
  private Order order;

  @ManyToOne
  @JoinColumn(name = "orga_id", nullable = false)
  private Organisation orga;

  @ManyToOne
  @JoinColumn(name = "court_id", nullable = false)
  private Court court;
  
  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @Column(name = "time_start", columnDefinition = "TIMESTAMP WITH TIME ZONE")
//  @Column(name = "time_start")
  private LocalDateTime timeStart;
  
//  @Column(name = "time_end")
  @Column(name = "time_end", columnDefinition = "TIMESTAMP WITH TIME ZONE")
  private LocalDateTime timeEnd; // dam bao time_start & time_end chung mot ngay (validation)

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private BookingStatus status = BookingStatus.PENDING;

  @OneToOne()
  @JoinColumn(name = "payment_id")
  private Payment payment;

  @Column
  private Double total;


  @Column(nullable = true)
  private boolean isReminded = false;

}
