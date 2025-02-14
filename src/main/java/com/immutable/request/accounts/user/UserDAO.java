package com.immutable.request.accounts.user;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.dependencies.utils.ResponseSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.request.assets.Asset;
import com.immutable.request.token.Token;
import com.immutable.request.utils.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")@CrossOrigin
public class UserDAO {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private final IJedis redis;

    @Autowired
    public UserDAO(@Qualifier("jedisImx") IJedis redis) {
        this.redis = redis;
    }

    @CrossOrigin
    @GetMapping("/accessibility")
    public Boolean userAccessibility() {
        return true;
    }

    @CrossOrigin
    @PostMapping(value = "/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<User> createUser(@RequestBody User user) throws IOException {
        redis.setByString(user.getGovernmentID().toString(), Formatter.toJSON(user));
        User newUser = Formatter.toObject(redis.getByString(user.getGovernmentID().toString()), User.class);
        return ResponseSchema.of(newUser, HttpStatus
                .CREATED,"created");
    }

    @PutMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE) @CrossOrigin
    public ResponseSchema<User> updateUser(@RequestBody User user) {
        redis.setByString(user.governmentID.toString(), Formatter.toJSON(user));
        User getUser =  Formatter.toObject(redis.getByString(user.getGovernmentID().toString()), User.class);
        return ResponseSchema.of(getUser,HttpStatus.OK,"update");
    }

    @CrossOrigin
    @DeleteMapping(value = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<User> deleteUser(@RequestParam String governmentId) {
        redis.setByString(governmentId,null);
        return ResponseSchema.of(null,HttpStatus.OK,"deleted");
    }

    @CrossOrigin
    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<UserDTO> getUser(@RequestParam String governmentId) {
        IJedis jedis = new JedisImx();
        UserDTO _userDTO = new UserDTO();
        User userDetails = Formatter.toObject(jedis.getByString(governmentId), User.class);
        List<String> currentUserTokens = userDetails.tokenIds;
        List<String>  currentUserAssets = userDetails.assetIds;
        _userDTO.user = userDetails;
        currentUserAssets.forEach( id -> _userDTO.assets.add(Formatter.toObject(jedis.getByString(id), Asset.class)));
        currentUserTokens.forEach( id -> _userDTO.tokens.add(Formatter.toObject(jedis.getByString(id), Token.class)));
        return ResponseSchema.of(Formatter.toObject(Formatter.toJSON(_userDTO),UserDTO.class),HttpStatus.OK,"get");
    }

    @CrossOrigin
    @PutMapping(value = "/addAsset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<User> addNewAsset(@RequestParam  String governmentId, @RequestBody Map<String,String> asset) throws IOException {
        String userDetails = redis.getByString(governmentId);
        if(userDetails == null){
            return  null;
        }
        User user = Formatter.convertToObject(userDetails, User.class);
        String id =  asset.get("assetId");
        if(user.assetIds.contains(id)){
            return  ResponseSchema.of(user,HttpStatus.NOT_FOUND,"exist_assetAdded");
        }
        user.assetIds.add(id);
        redis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return ResponseSchema.of(Formatter.toObject(redis.getByString(Long.toString(user.governmentID)), User.class),HttpStatus.OK,"assetAdded");
    }

    @CrossOrigin
    @PutMapping (value = "/addToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseSchema<User> addNewToken(@RequestParam  String governmentId, @RequestBody Map<String,String> token) throws JsonProcessingException {
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        if(userDetails == null){
            return  null;
        }
        User user = Formatter.convertToObject(userDetails, User.class);
        user.tokenIds.add(token.get("tokenId"));
        jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
        return ResponseSchema.of( Formatter.toObject(redis.getByString(Long.toString(user.governmentID)), User.class),HttpStatus.OK,"tokenAdded");
    }

    @CrossOrigin @PutMapping(value = "/removeAsset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<User> removeAsset(@RequestParam  String governmentId, @RequestBody Map<String,String> asset) throws IOException{
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(governmentId);
        User user = Formatter.convertToObject(userDetails, User.class);
        String id =  asset.get("assetId");
        if(user.assetIds.contains(id)){
            user.assetIds.remove(id);
            jedis.setByString(Long.toString(user.governmentID), Formatter.toJSON(user));
            return ResponseSchema.of(user,HttpStatus.ACCEPTED,"removeAsset");
        }
        return ResponseSchema.of(Formatter.toObject(redis.getByString(Long.toString(user.governmentID)), User.class),HttpStatus.OK,"removeAsset");
    }
}
