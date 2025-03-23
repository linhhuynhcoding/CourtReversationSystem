package com.app.CourtReservationSystem.dto;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Data
public class ErrorResponse<E> {
    private Date timestamp;
    private String message;
    private E error;
    private String path;
    private String details = "";
    private String help = "";
    
    public ErrorResponse(Date timestamp, String message, E error, String path, String details , String help){
        this.timestamp = timestamp;
        this.message=message;
        this.error=error;
        this.path=path;
        this.details=details;
        this.help=help;
    }
    
    
}

