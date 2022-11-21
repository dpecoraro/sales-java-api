package org.udemy.spring.course.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.udemy.spring.course.domain.bo.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@PropertySource("classpath:security.properties")
public class JwtService {
    @Value("30")
    private String duration;
    @Value("cXNhdWRlIHJ1bGVzCg==")
    private String signKey;

    public String generateToken(User user) {
        long duration = Long.valueOf(this.duration);
        LocalDateTime expirateAt = LocalDateTime.now().plusMinutes(duration);
        Date date = Date.from(expirateAt
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, signKey)
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date expiration = claims.getExpiration();
            LocalDateTime localDateTime = expiration.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            return LocalDateTime.now().isBefore(localDateTime);
        } catch (Exception ex) {
            return false;
        }
    }

    public String getUserLogin(String token) {
        return getClaims(token).getSubject();
    }
}
