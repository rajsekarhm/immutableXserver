package com.immutable.request.controller;

import com.immutable.request.accounts.UserIMX;

public class UserFactory implements AccountFactory<UserIMX> {

    public UserIMX create(UserIMX createEntity){
        return  new UserIMX.Builder().build();
    }
}
