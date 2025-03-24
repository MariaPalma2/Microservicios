package com.maria.api_gateway.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "865sNxRFfAsoaJnU6meP6ZCwcrMi2irkrTaGbwRBXDHA2TyzkkZrbnQw4yw1";

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public Claims validateToken(String token) throws JwtException {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
