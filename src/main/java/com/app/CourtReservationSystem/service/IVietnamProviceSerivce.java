package com.app.CourtReservationSystem.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface IVietnamProviceSerivce {
    
    List<?> getAllCities();
    List<?> getAllDistricts(Long city_code);
    List<?> getAllWards(Long district_code);
}
