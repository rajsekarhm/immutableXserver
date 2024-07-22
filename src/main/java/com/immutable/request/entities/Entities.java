package com.immutable.request.entities;

import com.immutable.request.accounts.UserIMX;
import com.immutable.request.controller.AccountFactory;
import com.immutable.request.controller.UserFactory;

public class Entities implements IEntitiesHandlers<UserIMX> {

    public UserIMX createUser(UserIMX account){
        AccountFactory<UserIMX> factory =  new UserFactory();
        return factory.create(account);
    }
//    public UserIMX getUser(){
//        UserIMX user = new UserIMX();
//        return user;
//    }
//    public UserIMX updateUser(){
//        UserIMX user = new UserIMX();
//        return user;
//    }
//    public UserIMX deleteUser(){
//        UserIMX user = new UserIMX();
//        return user;
//    }
}
