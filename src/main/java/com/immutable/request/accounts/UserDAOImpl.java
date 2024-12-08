package com.immutable.request.accounts;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.immutable.request.entities.UserDB_Handlers;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;

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
        // handle associated user infos
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        if(userDetails == null){
            return  null;
        }
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        String id =  asset.get("assetId");
        if(user.assetIds.contains(id)){
            return  user;
        }
        user.assetIds.add(id);
        jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return user;
    }

    @CrossOrigin
    @PutMapping ("/addToken")
    public  Object addNewToken(@RequestParam  String governmentId,@RequestBody Map<String,String> token) throws JsonProcessingException {
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        if(userDetails == null){
            return  null;
        }
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        user.tokenIds.add(token.get("tokenId"));
        jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return user;
    }

    @CrossOrigin @PutMapping("/removeAsset")
    public Object removeAsset(@RequestParam  String governmentId,  @RequestBody Map<String,String> asset) throws IOException{
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        String id =  asset.get("assetId");
        if(user.assetIds.contains(id)){
            user.assetIds.remove(id);
            jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
            return user;
        }
        return user;

    }
}
