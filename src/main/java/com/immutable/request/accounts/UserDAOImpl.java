package com.immutable.request.accounts;

import com.immutable.authentication.AuthenticationUser;
import com.immutable.authentication.IAuthentication;
import com.immutable.authorization.AuthorizationUser;
import com.immutable.authorization.IAuthorization;
import com.immutable.request.entities.Entities;
import com.immutable.request.entities.UserDB_Handlers;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/v1/user")
public class UserDAOImpl {

    @GetMapping("/accessibility")
    public Boolean userAccessibility() {
        return true;
    }

    @RequestMapping("/createUser")
    public String createUser(UserDAO.Builder user) {
        UserDAO u = new UserDAO();
        System.out.println(u.toString());
        UserDB_Handlers userBD = new UserDB_Handlers();
        userBD.createEntities();
        return "ok";
    }

    @PutMapping("/updateUser")
    public String updateUser(UserDAO.Builder user) {
        UserDAO u = new UserDAO();
        System.out.println(u.toString());
        UserDB_Handlers userBD = new UserDB_Handlers();
        userBD.updateEntities();
        return "ok";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(Long queryId) {
        UserDB_Handlers userBD = new UserDB_Handlers();
        userBD.deleteEntities();
        return "ok";
    }

    @GetMapping("/getUser")
    public String getUser(Long queryId) {
         UserDAO u = new UserDAO();
        System.out.println( u.toString());
        UserDB_Handlers userBD = new UserDB_Handlers();
        userBD.getEntities();
        return "ok";
    }
}
