package com.immutable.authentication;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * JWT authentication filter that runs before Spring Security's default filters.
 *
 * Resolution order:
 *   1. Authorization: Bearer <token>  (header)
 *   2. auth-token cookie              (fallback)
 *
 * If a valid token is found, it sets the SecurityContext with the user's identity
 * so downstream controllers can access the authenticated principal.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = resolveToken(request);

        if (token != null) {
            Claims claims = jwtService.validateToken(token);
            if (claims != null) {
                String userId = claims.getSubject();
                String role = claims.get("role", String.class);

                // Create Spring Security authentication with role-based authority
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userId,
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                        );

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Tries Authorization header first, then falls back to the cookie.
     */
    private String resolveToken(HttpServletRequest request) {
        // 1. Check Authorization: Bearer <token>
        String bearerHeader = request.getHeader("Authorization");
        if (bearerHeader != null && bearerHeader.startsWith("Bearer ")) {
            return bearerHeader.substring(7);
        }

        // 2. Fallback to cookie
        return CookieUtil.extractTokenFromCookies(request.getCookies());
    }
}
