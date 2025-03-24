package com.maria.autenticacion.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    public static final String USER = "admin";
    public static final String PASSWORD = "1234";

    private static final String SECRET_KEY = "865sNxRFfAsoaJnU6meP6ZCwcrMi2irkrTaGbwRBXDHA2TyzkkZrbnQw4yw1";
    private static final long EXPIRATION_TIME = 36000000000L;

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}