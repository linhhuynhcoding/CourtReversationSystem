package com.app.CourtReservationSystem.exception;

import com.app.CourtReservationSystem.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  // Catch APIException.class
  @ExceptionHandler(APIException.class)
  public ResponseEntity<ErrorResponse> handleAPIException(Exception e, WebRequest webRequest) {
    ErrorResponse errorResponse = new ErrorResponse(new Date(), e.getMessage(), webRequest.getDescription(false));

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  // Catch APIException.class
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleNotValidException(Exception e, WebRequest webRequest) {
    ErrorResponse errorResponse = new ErrorResponse(new Date(), "Validation failed ", webRequest.getDescription(false));

    return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
  }


  // Catch Exception.class
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGlobalException(Exception e, WebRequest webRequest) {
    // logging strack trace
    log.trace(e.getStackTrace().toString());
    ErrorResponse errorResponse = new ErrorResponse(new Date(), "SERVER ERROR", webRequest.getDescription(false));
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
