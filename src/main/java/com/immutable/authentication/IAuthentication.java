package com.immutable.authentication;

public interface IAuthentication {
    public Boolean isValidUser();
    public String getJWTToken();
}
