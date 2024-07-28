package com.immutable.authorization;

import com.immutable.request.accounts.CustodianDAO;
import com.immutable.request.accounts.UserDAO;

import java.util.Arrays;
import java.util.List;

public class AuthorizationUser implements IAuthorization{
    public Boolean isEditionUser(){
        return  true;
    }
    public List<String> accessibilty(){
        List<String> list = Arrays.asList("a");
        return  list;
    }
}
