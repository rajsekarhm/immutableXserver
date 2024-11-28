package com.immutable.request.accounts;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/user")@CrossOrigin
public class UserDAOImpl {

    @CrossOrigin
    @GetMapping("/accessibility")
    public Boolean userAccessibility() {
        return true;
    }
    @CrossOrigin
    @PostMapping("/createUser")
    public Object createUser(@RequestBody  UserDAO user) throws IOException {
        IJedis jedis = new JedisImx();
        String userJson = new Gson().toJson(user);
        jedis.setByString(user.getGovernmentID().toString(), userJson);
        return user;
    }

    @PutMapping("/updateUser") @CrossOrigin
    public String updateUser(@RequestBody UserDAO user) {
        IJedis jedis = new JedisImx();
        jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return "ok";
    }

    @CrossOrigin
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam Long governmentId) {
        UserDB_Handlers userBD = new UserDB_Handlers();
        userBD.deleteEntities();
        return "ok";
    }

    @CrossOrigin
    @GetMapping("/getUser")
    public Object getUser(@RequestParam String governmentId) {
        IJedis jedis = new JedisImx();
        return Formatter.toJSON(jedis.getByString(governmentId));
    }

    @CrossOrigin
    @PutMapping("/addAsset")
    public Object addNewAsset(@RequestParam  String governmentId,  @RequestBody Map<String,String> asset) throws IOException {
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        user.assetIds.add(asset.get("assetId"));
        jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return user;
    }

    @CrossOrigin
    @PutMapping ("/addToken")
    public  Object addNewToken(@RequestParam  String governmentId,@RequestBody Map<String,String> token) throws JsonProcessingException {
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        user.tokenIds.add(token.get("tokenId"));
        jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return user;
    }
}
