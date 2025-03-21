package com.app.CourtReservationSystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class APIException extends RuntimeException {
  private String message;
  private HttpStatus httpStatus;
}
