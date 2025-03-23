package com.app.CourtReservationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private Date timestamp;
    private boolean success;
    private String message;
    private String details;
    private String path;
    private T data;
    
    public ApiResponse(String message, String details, String path, T data) {
        this.timestamp = new Date();
        this.success = true;
        this.message = message;
        this.details = details;
        this.path = path;
        this.data = data;
    }
}