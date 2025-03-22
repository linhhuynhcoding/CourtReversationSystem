package com.app.CourtReservationSystem.dto;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Data
public class ErrorResponse<E> {
  private Date timestamp;
  private String message;
  private String details = "";
  private String help = "";
  private E error;
  private String path;

}

