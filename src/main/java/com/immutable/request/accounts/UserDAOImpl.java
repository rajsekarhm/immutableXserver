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
    public String createUser() {
        return "ok";
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestParam String userName,
                             @RequestParam String email,
                             @RequestParam Boolean isAgent,
                             @RequestParam Long phoneNumber,
                             @RequestParam String edition,
                             @RequestParam Boolean isAuthForBuyAndSell,
                             @RequestParam Long governmentID) {
        return "ok";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser() {
        return "ok";
    }

    @GetMapping("/getUser")
    public String getUser() {
        return "ok";
    }
}
