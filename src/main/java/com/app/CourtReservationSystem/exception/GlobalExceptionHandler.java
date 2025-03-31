package com.app.CourtReservationSystem.exception;

import com.app.CourtReservationSystem.dto.ErrorResponse;
import com.app.CourtReservationSystem.dto.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    // Catch
    // APIException.class
    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponse<String>> handleAPIException(Exception e, WebRequest webRequest,
                                                                    HttpServletRequest httpServletRequest) {
        ErrorResponse<String> errorResponse = new ErrorResponse<String>(new Date(), "API Error", "",
            webRequest.getDescription(false), "", "");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    // Catch
    // ResourceNotFoundException.class
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception e, WebRequest webRequest,
                                                                 HttpServletRequest httpServletRequest) {
        ErrorResponse<String> errorResponse = new ErrorResponse<String>(new Date(), e.getMessage(), "",
            httpServletRequest.getRequestURI(),
            "", "");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    // Catch
    // MethodArgumentNotValidException.class
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse<?>> handleNotValidException(MethodArgumentNotValidException e,
                                                                    WebRequest webRequest,
                                                                    HttpServletRequest httpServletRequest) {
        List<ValidationErrorResponse> validationErrorResponses =
            e.getBindingResult().getFieldErrors().stream().map(f -> new ValidationErrorResponse(f.getField(),
                f.getDefaultMessage())).collect(Collectors.toList());
        
        ErrorResponse<List<ValidationErrorResponse>> errorResponse = new ErrorResponse<>(new Date(), "Validation " +
            "fail!", validationErrorResponses, httpServletRequest.getRequestURI(), "", "");
        
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }
    
    // Catch
    // Exception.class
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e, WebRequest webRequest,
                                                               HttpServletRequest httpServletRequest) {

        e.printStackTrace();
        
        ErrorResponse<String> errorResponse = new ErrorResponse<String>(new Date(), "Server Error", "",
            httpServletRequest.getRequestURI(),
            "", "");
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
