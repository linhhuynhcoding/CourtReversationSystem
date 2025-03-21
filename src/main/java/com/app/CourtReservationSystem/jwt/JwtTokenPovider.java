package com.app.CourtReservationSystem.jwt;

import com.app.CourtReservationSystem.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenPovider {

        @Autowired
        private AccountRepository accountRepository;

        @Value("${spring.application.jwt_secret_key}")
        private String JWT_SECRET_KEY;

        @Value("${spring.application.jwt_expired_time}")
        private String JWT_EXPIRED_TIME;

        // Tạo ra jwt từ thông tin user
        public String generateToken(CustomUserDetails userDetails) {
                Date now = new Date();
                Date expiryDate = new Date(now.getTime() + JWT_EXPIRED_TIME);
                // Tạo chuỗi json web token từ id của user.
                return Jwts.builder()
                        .setSubject(Long.toString(userDetails.getAccount().getId()))
                        .setIssuedAt(now)
                        .setExpiration(expiryDate)
                        .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                        .compact();
        }

        // Lấy thông tin user từ jwt
        public Long getUserIdFromJWT(String token) {
                Claims claims = Jwts.parser()
                        .setSigningKey(JWT_SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();

                return Long.parseLong(claims.getSubject());
        }

        public boolean validateToken(String authToken) {
                try {
                        Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(authToken);
                        return true;
                } catch (MalformedJwtException ex) {
                        log.error("Invalid JWT token");
                } catch (ExpiredJwtException ex) {
                        log.error("Expired JWT token");
                } catch (UnsupportedJwtException ex) {
                        log.error("Unsupported JWT token");
                } catch (IllegalArgumentException ex) {
                        log.error("JWT claims string is empty.");
                }
                return false;
        }
}
