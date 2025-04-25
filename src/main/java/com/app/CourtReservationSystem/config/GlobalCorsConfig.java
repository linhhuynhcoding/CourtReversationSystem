package com.app.CourtReservationSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS policy to all endpoints
                .allowedOrigins("http://localhost:3000", "http://172.19.128.1" +
                        ":3000", "http://192.168.1.4:3000", "https://court-reservation-web-git-main-linhhuynhcodings-projects.vercel.app", "http://192.168.1.250:3000", "https://court-reservation-web.vercel.app") // Allowed origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH") // HTTP methods
                .allowedHeaders("Authorization", "Content-Type") // Allowed headers
                .allowCredentials(true); // Allow credentials (cookies, Authorization headers, etc.)
    }
}
