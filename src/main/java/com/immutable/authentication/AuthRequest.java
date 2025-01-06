package com.immutable.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter @Setter
public class AuthRequest {
    String userName;
    String password;

    public AuthRequest(){}
}
