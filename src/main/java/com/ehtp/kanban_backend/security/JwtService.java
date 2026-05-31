package com.ehtp.kanban_backend.security;

import com.ehtp.kanban_backend.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtService {
    private static final long EXPIRATION_SECONDS = 3600L;
    private static final String SECRET_KEY_BASE64 = "u4r0cG2OZ0hT4Cngk6j8Xr7d+f0Gd0oQ8pH3c3qvO2U=";

    String generateToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + (EXPIRATION_SECONDS * 1000));

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .claim("role", user.getRole().name())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    boolean isTokenValid(String token) {
        Date expirationDate = extractAllClaims(token).getExpiration();
        return expirationDate.after(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY_BASE64);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
