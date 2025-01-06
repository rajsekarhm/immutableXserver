package com.immutable.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth") @CrossOrigin
public class AuthController implements IAuthentication {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  JWTTokenUtil jwtUtil;

    public  String authenticate(@RequestBody AuthRequest authRequest) throws Exception {
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getUserName()));
        }catch (AuthenticationException error){
            throw new Exception("Invalid username or password", error  );

        }
        return  jwtUtil.generateToken(authRequest.getUserName());
    }

}