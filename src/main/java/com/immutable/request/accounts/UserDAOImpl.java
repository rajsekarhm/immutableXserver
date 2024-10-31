package com.immutable.request.accounts;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.google.gson.Gson;
import com.immutable.authentication.AuthenticationUser;
import com.immutable.authentication.IAuthentication;
import com.immutable.authorization.AuthorizationUser;
import com.immutable.authorization.IAuthorization;
import com.immutable.request.entities.Entities;
import com.immutable.request.entities.UserDB_Handlers;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/user")
public class UserDAOImpl {

    @GetMapping("/accessibility")
    public Boolean userAccessibility() {
        return true;
    }

    @RequestMapping("/createUser")
    public String createUser(@RequestBody  UserDAO.Builder user) throws IOException {
        IJedis jedis = new JedisImx();
        jedis.setByString(user.governmentID.toString(), Formatter.toJSON(user));
        return "ok";
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody UserDAO.Builder user) {
//        UserDAO u = new UserDAO();
//        System.out.println(u.toString());
//        UserDB_Handlers userBD = new UserDB_Handlers();
//        userBD.updateEntities();
        IJedis jedis = new JedisImx();
        jedis.setByString(user.governmentID.toString(), Formatter.toJSON(user));
        return "ok";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestBody Long queryId) {
        UserDB_Handlers userBD = new UserDB_Handlers();
        userBD.deleteEntities();
        return "ok";
    }

    @GetMapping("/getUser")
    public String getUser(@RequestParam String queryId) {
//        UserDAO u = new UserDAO();
//        System.out.println( u.toString());
//        UserDB_Handlers userBD = new UserDB_Handlers();
//        userBD.getEntities(queryId);
        IJedis jedis = new JedisImx();
        return jedis.getByString(queryId);
    }
}
