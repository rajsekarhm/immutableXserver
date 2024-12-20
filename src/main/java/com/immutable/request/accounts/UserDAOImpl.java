package com.immutable.request.accounts;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.request.entities.UserDB_Handlers;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")@CrossOrigin
public class UserDAOImpl {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private  IJedis redis = new JedisImx();
    @CrossOrigin
    @GetMapping("/accessibility")
    public Boolean userAccessibility() {
        return true;
    }

    @CrossOrigin
    @PostMapping("/createUser")
    public UserDAO createUser(@RequestBody  UserDAO user) throws IOException {
        redis.setByString(user.getGovernmentID().toString(), Formatter.toJSON(user));
        return Formatter.toObject(redis.getByString(user.getGovernmentID().toString()),UserDAO.class);
    }

    @PutMapping("/updateUser") @CrossOrigin
    public UserDAO updateUser(@RequestBody UserDAO user) {
        redis.setByString(user.governmentID.toString(), Formatter.toJSON(user));
        return Formatter.toObject(redis.getByString(user.getGovernmentID().toString()),UserDAO.class);
    }

    @CrossOrigin
    @DeleteMapping("/deleteUser")
    public Object deleteUser(@RequestParam String governmentId) {
        redis.setByString(governmentId,null);
        return null;
    }

    @CrossOrigin
    @GetMapping("/getUser")
    public UserDAO getUser(@RequestParam String governmentId) {
        return Formatter.toObject(redis.getByString(governmentId),UserDAO.class);
    }

    @CrossOrigin
    @PutMapping("/addAsset")
    public UserDAO addNewAsset(@RequestParam  String governmentId,  @RequestBody Map<String,String> asset) throws IOException {
        // handle associated user info
        String userDetails = redis.getByString(governmentId);
        if(userDetails == null){
            return  null;
        }
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        String id =  asset.get("assetId");
        if(user.assetIds.contains(id)){
            return  user;
        }
        user.assetIds.add(id);
        redis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return Formatter.toObject(redis.getByString(Long.toString(user.governmentID)),UserDAO.class);
    }

    @CrossOrigin
    @PutMapping ("/addToken")
    public  UserDAO addNewToken(@RequestParam  String governmentId,@RequestBody Map<String,String> token) throws JsonProcessingException {
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        if(userDetails == null){
            return  null;
        }
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        user.tokenIds.add(token.get("tokenId"));
        jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return Formatter.toObject(redis.getByString(Long.toString(user.governmentID)),UserDAO.class);
    }

    @CrossOrigin @PutMapping("/removeAsset")
    public UserDAO removeAsset(@RequestParam  String governmentId,  @RequestBody Map<String,String> asset) throws IOException{
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        String id =  asset.get("assetId");
        if(user.assetIds.contains(id)){
            user.assetIds.remove(id);
            jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
            return user;
        }
        return Formatter.toObject(redis.getByString(Long.toString(user.governmentID)),UserDAO.class);
    }
}
