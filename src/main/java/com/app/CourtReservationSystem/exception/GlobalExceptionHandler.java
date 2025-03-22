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
    public ResponseEntity<ErrorResponse<String>> handleAPIException(Exception e, WebRequest webRequest) {
        ErrorResponse<String> errorResponse = new ErrorResponse<String>(
                new Date(), "API Error", "", "", "", webRequest.getContextPath()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Catch ResourceNotFoundException.class
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e, WebRequest webRequest) {
        ErrorResponse<String> errorResponse = new ErrorResponse<String>(
                new Date(), "API Error", "", "", "", webRequest.getContextPath()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Catch MethodArgumentNotValidException.class
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleNotValidException(Exception e, WebRequest webRequest) {
        System.out.println(e);
        ErrorResponse<String> errorResponse = new ErrorResponse<String>(
                new Date(), "API Error", "", "", "", webRequest.getContextPath()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // Catch Exception.class
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e, WebRequest webRequest) {
        // logging strack trace
        log.trace(e.getStackTrace().toString());
        System.out.println(e.getStackTrace().toString());
        ErrorResponse<String> errorResponse = new ErrorResponse<String>(
                new Date(), "API Error", "", "", "", webRequest.getContextPath()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
