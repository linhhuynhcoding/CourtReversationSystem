package com.app.CourtReservationSystem.service.Impl;

import com.app.CourtReservationSystem.service.ICacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {
    private static Logger logger = LoggerFactory.getLogger(RedisService.class);
    
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    
    public void saveData(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        logger.info("SET CACHE\t" + key);
    }
    
    public void saveDataWithTTL(String key, Object value, long ttl, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, ttl, timeUnit);
        logger.info("SET CACHE WITH TTL\t" + key);
    }
    
    public Object getData(String key) {
        var data = redisTemplate.opsForValue().get(key);
        
        
        if (data == null) {
            logger.info("CACHE MISS\t" + key);
        }
        else {
            logger.info("CACHE HIT\t" + key);
        }
        
        return data;
    }
    
    public void deleteData(String key) {
        redisTemplate.delete(key);
        logger.info("DELETE CACHE:" + key);
    }
    
    public void clearCache() {
        redisTemplate.getConnectionFactory().getConnection().serverCommands().flushAll();
        logger.info("CLEAR ALL CACHE");
    }
    
    
}
