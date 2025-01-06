package com.immutable.authentication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode @Entity @Component
public class AuthUserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long userId;
    private  String userName;
    private  String password ;
    private List<String> roles = new ArrayList<>();
    // other methods

    public AuthUserDTO(){}

    public List<String> getRoles() {
        return new ArrayList<>();
    }

    public String getPassword() {
        return "password";
    }

    public String getUserName() {
        return "userName";
    }

    public long getUserId() {
        return 123456;
    }
}
