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
    private T data;
}