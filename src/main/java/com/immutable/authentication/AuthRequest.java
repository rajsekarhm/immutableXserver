package com.immutable.authentication;

public class AuthRequest {
    String userName;
    String password;

    public AuthRequest(){}

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
