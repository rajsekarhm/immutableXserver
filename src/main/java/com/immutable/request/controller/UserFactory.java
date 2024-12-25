package com.immutable.request.controller;

import com.immutable.request.accounts.user.UserDAO;

public class UserFactory implements AccountFactory<UserDAO> {

    public UserDAO create(UserDAO createEntity){
        return new UserDAO.Builder().build();
    }
}
