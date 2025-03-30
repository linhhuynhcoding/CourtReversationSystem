package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.service.ICacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RedisService implements ICacheService {
    public RedisService(@Value("${spring.application.redis_url}") String REDIS_URL) {
//        super(REDIS_URL);
    }
}
