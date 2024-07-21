package com.immutable.authorization;

import java.util.Arrays;
import java.util.List;

public class AuthorizationUser implements IAuthorization<String>{
    public Boolean isEditionUser(){
        return  true;
    }
    public List<String> accessibilty(){
        List<String> list = Arrays.asList("a","b");
        return  list;
    }
}
