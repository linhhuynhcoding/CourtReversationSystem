package com.app.CourtReservationSystem.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.UnifiedJedis;

@Service
public class RedisService extends UnifiedJedis {
    @Value("${spring.application.redis_url}")
    private static String REDIS_URL;
    
    public RedisService() {
        super(REDIS_URL);
    }
}
