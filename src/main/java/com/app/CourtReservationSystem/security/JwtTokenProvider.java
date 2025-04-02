package com.app.CourtReservationSystem.security;

import com.app.CourtReservationSystem.repository.AccountRepository;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtTokenProvider {

        @Autowired
        AccountRepository accountRepository;

        @Value("${spring.application.jwt_secret_key}")
        private String JWT_SECRET_KEY;

        @Value("${spring.application.jwt_expired_time}")
        private long JWT_EXPIRED_TIME;

        // Tạo ra jwt từ thông tin user
        public String generateToken(Authentication authentication) {

                Date now = new Date();
                Date expiryDate = new Date(now.getTime() + JWT_EXPIRED_TIME);
                String username = authentication.getName();
                String role = accountRepository.findByUsername(username).getAccountRole().getRole();
                // Tạo chuỗi json web token từ username của user.
                
                return Jwts.builder()
                        .subject(authentication.getName())
                        .claim("role", role)
                        .issuedAt(now)
                        .expiration(expiryDate)
                        .signWith(key())
                        .compact();
        }
        
        private Key key(){
                return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET_KEY));
        }

        public String getUsername(String token) {
          Claims claims = Jwts.parser()
            .verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload();
            
          return claims.getSubject();
        }
        

        public boolean validateToken(String authToken) {
                try {
                        Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
//                        Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(authToken);
//                        extractClaim();

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
