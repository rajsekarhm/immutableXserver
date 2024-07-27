package com.immutable.request.accounts;

import com.immutable.authentication.AuthenticationUser;
import com.immutable.authorization.AuthorizationUser;
import com.immutable.authorization.IAuthorization;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDAOImpl {

    public  Boolean UserAccessibility(){
        return  true;
    }
    @RequestMapping("/createUser")
    public  void  createUser(@RequestBody UserDAO createUser){
        IAuthorization authorUser  = new AuthorizationUser();
        AuthenticationUser authenUser = new AuthenticationUser();
        UserDAO handleUser =  new UserDAO.Builder().build();
    }
    @RequestMapping("/updateUser")
    public  void updateUser(@RequestBody UserDAO updateUser){

    }
    @RequestMapping("/deleteUser")
    public  void  deleteUser(@RequestBody UserDAO deleteUser){

    }
    @RequestMapping("/getUser")
    public  void getUser(@RequestBody UserDAO getUser){

    }
}
