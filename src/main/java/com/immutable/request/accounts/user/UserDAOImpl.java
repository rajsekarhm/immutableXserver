package com.immutable.request.accounts.user;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.dependencies.utils.ResponseSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.request.utils.Formatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<UserDAO> createUser(@RequestBody  UserDAO user) throws IOException {
        redis.setByString(user.getGovernmentID().toString(), Formatter.toJSON(user));
        UserDAO newUser = Formatter.toObject(redis.getByString(user.getGovernmentID().toString()),UserDAO.class);
        return ResponseSchema.of(newUser, HttpStatus
                .CREATED,"created");
    }

    @PutMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE) @CrossOrigin
    public ResponseSchema<UserDAO> updateUser(@RequestBody UserDAO user) {
        redis.setByString(user.governmentID.toString(), Formatter.toJSON(user));
        UserDAO getUser =  Formatter.toObject(redis.getByString(user.getGovernmentID().toString()),UserDAO.class);
        return ResponseSchema.of(getUser,HttpStatus.OK,"update");
    }

    @CrossOrigin
    @DeleteMapping(value = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<UserDAO> deleteUser(@RequestParam String governmentId) {
        redis.setByString(governmentId,null);
        return ResponseSchema.of(null,HttpStatus.OK,"deleted");
    }

    @CrossOrigin
    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<UserDTO> getUser(@RequestParam String governmentId) {
        UserDTO user = new UserDTO();
        return ResponseSchema.of(Formatter.toObject(Formatter.toJSON(user.getUserDetails(governmentId)),UserDTO.class),HttpStatus.OK,"get");
    }

    @CrossOrigin
    @PutMapping(value = "/addAsset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<UserDAO> addNewAsset(@RequestParam  String governmentId, @RequestBody Map<String,String> asset) throws IOException {
        String userDetails = redis.getByString(governmentId);
        if(userDetails == null){
            return  null;
        }
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        String id =  asset.get("assetId");
        if(user.assetIds.contains(id)){
            return  ResponseSchema.of(user,HttpStatus.NOT_FOUND,"exist_assetAdded");
        }
        user.assetIds.add(id);
        redis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return ResponseSchema.of(Formatter.toObject(redis.getByString(Long.toString(user.governmentID)),UserDAO.class),HttpStatus.OK,"assetAdded");
    }

    @CrossOrigin
    @PutMapping (value = "/addToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseSchema<UserDAO> addNewToken(@RequestParam  String governmentId,@RequestBody Map<String,String> token) throws JsonProcessingException {
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        if(userDetails == null){
            return  null;
        }
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        user.tokenIds.add(token.get("tokenId"));
        jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return ResponseSchema.of( Formatter.toObject(redis.getByString(Long.toString(user.governmentID)),UserDAO.class),HttpStatus.OK,"tokenAdded");
    }

    @CrossOrigin @PutMapping(value = "/removeAsset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<UserDAO> removeAsset(@RequestParam  String governmentId,  @RequestBody Map<String,String> asset) throws IOException{
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        UserDAO user = Formatter.convertToObject(userDetails,UserDAO.class);
        String id =  asset.get("assetId");
        if(user.assetIds.contains(id)){
            user.assetIds.remove(id);
            jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
            return ResponseSchema.of(user,HttpStatus.ACCEPTED,"removeAsset");
        }
        return ResponseSchema.of(Formatter.toObject(redis.getByString(Long.toString(user.governmentID)),UserDAO.class),HttpStatus.OK,"removeAsset");
    }
}
