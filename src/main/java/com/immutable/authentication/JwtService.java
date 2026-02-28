package com.immutable.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * JWT token creation, parsing, and validation.
 *
 * Token payload:
 *   sub  — user's unique identifier (securityId)
 *   name — display name (firstName + lastName)
 *   role — "user" | "custodian"
 *   iat  — issued-at
 *   exp  — expiry (24 h default)
 */
@Service
public class JwtService {

    private static final long TOKEN_VALIDITY_MS = 24 * 60 * 60 * 1000L; // 24 hours

    @Value("${jwt.secret:immutable-platform-super-secret-key-change-in-production-256bit!!}")
    private String secretString;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generates a signed JWT for the given user identity.
     */
    public String generateToken(String userId, String displayName, String role) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .subject(userId)
                .claims(Map.of(
                        "name", displayName,
                        "role", role
                ))
                .issuedAt(new Date(now))
                .expiration(new Date(now + TOKEN_VALIDITY_MS))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Parses and validates the token. Returns claims on success, null on failure.
     */
    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return null; // token expired
        } catch (JwtException e) {
            return null; // invalid signature / malformed
        }
    }

    /**
     * Extracts the subject (userId / securityId) from a valid token.
     */
    public String getUserId(String token) {
        Claims claims = validateToken(token);
        return claims != null ? claims.getSubject() : null;
    }

    /**
     * Extracts the role from a valid token.
     */
    public String getRole(String token) {
        Claims claims = validateToken(token);
        return claims != null ? claims.get("role", String.class) : null;
    }

    public long getTokenValiditySeconds() {
        return TOKEN_VALIDITY_MS / 1000;
    }
}
