package com.spring.ai.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET =
            "spring-ai-enterprise-jwt-secret-key-2026-secure-very-long";

    private final Key key =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes(StandardCharsets.UTF_8)
            );

    public String generateToken(String username) {

        return Jwts.builder()

                .setSubject(username)

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(System.currentTimeMillis()
                                + 1000 * 60 * 60)
                )

                .signWith(key, SignatureAlgorithm.HS256)

                .compact();
    }

    public String extractUsername(String token) {

        Claims claims = Jwts.parserBuilder()

                .setSigningKey(key)

                .build()

                .parseClaimsJws(token)

                .getBody();

        return claims.getSubject();
    }

    public boolean validate(String token) {

        try {

            Jwts.parserBuilder()

                    .setSigningKey(key)

                    .build()

                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}