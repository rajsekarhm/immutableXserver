package com.immutable.request.controller;

import com.immutable.request.accounts.user.User;

public class UserFactory implements AccountFactory<User> {

    public User create(User createEntity){
        return new User.Builder().build();
    }
}
