package com.immutable.authentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;

/**
 * Utility for creating and clearing the auth-token HttpOnly cookie.
 */
public final class CookieUtil {

    public static final String AUTH_COOKIE_NAME = "auth-token";

    private CookieUtil() {} // utility class

    /**
     * Adds a Set-Cookie header with the JWT token.
     * HttpOnly  — JS cannot read (prevents XSS theft)
     * Secure    — only sent over HTTPS
     * SameSite  — Strict (prevents CSRF)
     * Path=/    — available on all routes
     * Max-Age   — token TTL in seconds
     */
    public static void addAuthCookie(HttpServletResponse response, String token, long maxAgeSeconds) {
        ResponseCookie cookie = ResponseCookie.from(AUTH_COOKIE_NAME, token)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(maxAgeSeconds)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    /**
     * Clears the auth cookie by setting Max-Age=0.
     */
    public static void clearAuthCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(AUTH_COOKIE_NAME, "")
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(0)
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }

    /**
     * Extracts the auth-token value from the request cookies array.
     */
    public static String extractTokenFromCookies(Cookie[] cookies) {
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (AUTH_COOKIE_NAME.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
