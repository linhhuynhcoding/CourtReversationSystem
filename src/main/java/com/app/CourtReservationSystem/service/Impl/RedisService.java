package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.service.ICacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    
    public void saveData(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    public void saveDataWithTTL(String key, Object value, long ttl, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, ttl, timeUnit);
    }
    
    public Object getData(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    
    public void deleteData(String key) {
        redisTemplate.delete(key);
    }
    
    public void clearCache() {
        redisTemplate.getConnectionFactory().getConnection().serverCommands().flushAll();
    }
    
    
}
