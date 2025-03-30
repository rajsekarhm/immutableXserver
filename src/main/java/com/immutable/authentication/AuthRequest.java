package com.immutable.authentication;

public class AuthRequest {
    String username;
    String password;

    public AuthRequest(){}

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
}
