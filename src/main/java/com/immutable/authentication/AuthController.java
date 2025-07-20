//package com.immutable.authentication;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin
//public class AuthController implements IAuthentication {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JWTTokenUtil jwtUtil;
//
//    @PostMapping("/auth")
//    public String authenticate(@RequestBody AuthRequest authRequest) throws Exception {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
//        } catch (AuthenticationException error) {
//            throw new Exception("Invalid username or password", error);
//        }
//        return jwtUtil.generateToken(authRequest.getUserName());
//    }
//}