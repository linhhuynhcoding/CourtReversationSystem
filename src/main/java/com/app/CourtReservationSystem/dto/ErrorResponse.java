package com.app.CourtReservationSystem.dto;

import lombok.AllArgsConstructor;

import java.util.Date;

@AllArgsConstructor
public class ErrorResponse {
  private Date timestamp;
  private String message;
  private String details;


  public Date getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }}
