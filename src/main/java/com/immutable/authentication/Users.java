package com.immutable.authentication;

import com.immutable.request.accounts.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {
    @Id
    private int id;
    private String username;
    byte[] salt;
    String hashedPassword;

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSalt() {
        return salt;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
