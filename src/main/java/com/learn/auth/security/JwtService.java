package com.learn.auth.security;

import com.learn.auth.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value("${env.jwt-secret}")
    private String secret;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    };

    public String generateToken( User user){
        return Jwts.builder()
                .header()
                .and()
                .signWith(getSecretKey())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .claim("userId", user.getId())
                .compact();
    }
    public Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
