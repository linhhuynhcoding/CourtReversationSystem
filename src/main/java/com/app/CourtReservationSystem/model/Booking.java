package com.app.CourtReservationSystem.model;
/**
 * @author linhhuynhcoding
 */

import com.app.CourtReservationSystem.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "bookings")
@Audited
@Data
public class Booking extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "order_id", nullable = true)
  private Long orderId;

  @ManyToOne
  @JoinColumn(name = "court_id")
  private Court court;
  
  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @Column(name = "time_start")
  private Date timeStart;
  
  @Column(name = "time_end")
  private Date timeEnd; // dam bao time_start & time_end chung mot ngay (validation)

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private BookingStatus status = BookingStatus.PENDING;

  @OneToOne()
  @JoinColumn(name = "payment_id")
  private Payment payment;
}
