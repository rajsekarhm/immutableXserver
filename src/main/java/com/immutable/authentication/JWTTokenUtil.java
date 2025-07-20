//package com.immutable.authentication;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JWTTokenUtil {
//    private String secret = "secret";
//    private long expirationTime = 1000 * 60 * 60 * 10;
//
//    public String generateToken(String userName) {
//        return Jwts.builder()
//                .setSubject(userName)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//    }
//
//    public boolean validateToken(String token, String username) {
//        String extractedUsername = extractUsername(token);
//        return username.equals(extractedUsername) && !isTokenExpired(token);
//    }
//
//    public String extractUsername(String token) {
//        return extractClaims(token).getSubject();
//    }
//
//    public boolean isTokenExpired(String token) {
//        return extractClaims(token).getExpiration().before(new Date());
//    }
//
//    private Claims extractClaims(String token) {
//        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//    }
//}