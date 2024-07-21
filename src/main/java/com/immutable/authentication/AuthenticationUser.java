package com.immutable.authentication;

public class AuthenticationUser implements IAuthentication {
    public  Boolean isValidUser(){
        return  true;
    }

    public  String getJWTToken(){
        return  "";
    }
}