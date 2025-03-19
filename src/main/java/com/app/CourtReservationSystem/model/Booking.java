package com.app.CourtReservationSystem.model;
/**
 * @author linhhuynhcoding
 */

import com.app.CourtReservationSystem.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "bookings")
@Data
public class Booking extends Audiable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "order_id")
  private Long orderId;

  @ManyToOne
  @JoinColumn(name = "court_id")
  private Court court;

  @Column(name = "date")
  private Date date;

  @Column(name = "time_start")
  private Timestamp timeStart;

  @Column(name = "hours")
  private Double hours;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private BookingStatus status;

  @Column(name = "payment_id")
  private Long paymentId;
}
