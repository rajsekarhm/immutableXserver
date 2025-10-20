package com.immutable.authentication;

public class AuthUser {
    private String username;
    private String password;
    private String securityId;

    // getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSecurityId() { return securityId; }
    public void setSecurityId(String securityId) { this.securityId = securityId; }
}
