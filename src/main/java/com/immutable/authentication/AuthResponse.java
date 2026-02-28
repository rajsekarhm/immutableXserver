package com.immutable.authentication;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.immutable.request.accounts.user.User;

/**
 * Response body for successful signup / login.
 *
 * Shape:
 * {
 *   "user": { ...user fields (password excluded via @JsonIgnore on User)... },
 *   "token": "eyJhbGciOiJIUz..."
 * }
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {
    private User user;
    private String token;

    public AuthResponse() {}

    public AuthResponse(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
