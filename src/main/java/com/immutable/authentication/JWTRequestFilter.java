//package com.immutable.authentication;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class JWTRequestFilter extends OncePerRequestFilter {
//    private final JWTTokenUtil jwtToken;
//    private final AuthenticationManager authenticationManager;
//
//    public JWTRequestFilter(JWTTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
//        this.jwtToken = jwtTokenUtil;
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws ServletException, IOException {
//        final String authorizationHeader = request.getHeader("Authorization");
//        String userName = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwt = authorizationHeader.substring(7);
//            userName = jwtToken.extractUsername(jwt);
//        }
//
//        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            if (jwtToken.validateToken(jwt, userName)) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                        userName,
//                        null,
//                        new ArrayList<>()
//                );
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}