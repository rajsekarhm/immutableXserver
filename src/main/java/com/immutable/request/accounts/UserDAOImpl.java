package com.immutable.request.accounts;

import com.immutable.authentication.AuthenticationUser;
import com.immutable.authentication.IAuthentication;
import com.immutable.authorization.AuthorizationUser;
import com.immutable.authorization.IAuthorization;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserDAOImpl {

    @GetMapping("/accessibility")
    public Boolean userAccessibility() {
        return true;
    }

    @RequestMapping("/createUser")
    public String createUser(UserDAO.Builder user) {
        UserDAO u = new UserDAO();
        System.out.println( u.toString());
        return "ok";
    }

    @PutMapping("/updateUser")
    public String updateUser(UserDAO.Builder user) {
        UserDAO u = new UserDAO();
        System.out.println( u.toString());
        return "ok";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(Long queryId) {
        UserDAO u = new UserDAO();
        System.out.println( u.toString());
        return "ok";
    }

    @GetMapping("/getUser")
    public String getUser(Long queryId) {
         UserDAO u = new UserDAO();
        System.out.println( u.toString());
        return "ok";
    }
}
