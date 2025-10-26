package com.immutable.request.accounts.user;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.dependencies.utils.ResponseSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.authentication.AuthUser;
import com.immutable.authentication.DefaultPassWordAuth;
import com.immutable.request.assets.Asset;
import com.immutable.request.token.Token;
import com.immutable.request.utils.Formatter;
import com.immutable.request.utils.UncertainResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
        redis.setByString(user.getsecurityId().toString(), Formatter.toJSON(user));
        User newUser = Formatter.toObject(redis.getByString(user.getsecurityId().toString()), User.class);
        newUser.password = DefaultPassWordAuth.register(newUser.securityId.toString(),newUser.password);
        newUser.tokenIds =  (newUser.tokenIds != null) ? newUser.tokenIds : new HashSet<>();
        newUser.assetIds =  (newUser.assetIds != null) ? newUser.assetIds : new HashSet<>();
        redis.setByString(user.getsecurityId().toString(), Formatter.toJSON(newUser));
        return ResponseSchema.of(newUser, HttpStatus
                .CREATED,"created");
    }

    @PutMapping(value = "/updateUser", produces = MediaType.APPLICATION_JSON_VALUE) @CrossOrigin
    public ResponseSchema<User> updateUser(@RequestBody User user) {
        redis.setByString(user.securityId.toString(), Formatter.toJSON(user));
        UserDTO _userDTO = new UserDTO();
        User getUser =  Formatter.toObject(redis.getByString(user.getsecurityId().toString()), User.class);
        return ResponseSchema.of(getUser,HttpStatus.OK,"update");
    }

    @CrossOrigin
    @DeleteMapping(value = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<User> deleteUser(@RequestParam String securityId) {
        redis.setByString(securityId,null);
        return ResponseSchema.of(null,HttpStatus.OK,"deleted");
    }

    @CrossOrigin
    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<UserDTO> getUser(@RequestParam String securityId) {
        IJedis jedis = new JedisImx();
        UserDTO _userDTO = new UserDTO();
        if(jedis.getByString(securityId)  == null){
            return  ResponseSchema.of(Formatter.toObject(Formatter.toJSON(new UserDTO()),UncertainResponse.class),HttpStatus.BAD_REQUEST,"No user found for the given orgId.");
        }
        User userDetails = Formatter.toObject(jedis.getByString(securityId), User.class);
        List<String> currentUserTokens = (userDetails.tokenIds == null) ? new ArrayList<>() : new ArrayList<>(new HashSet<>(userDetails.tokenIds));
        List<String>  currentUserAssets = (userDetails.assetIds == null) ? new ArrayList<>() : new ArrayList<>(new HashSet<>(userDetails.assetIds));
        _userDTO.user = userDetails;
        currentUserAssets.forEach( id -> _userDTO.assets.add(Formatter.toObject(jedis.getByString(id), Asset.class)));
        currentUserTokens.forEach( id -> _userDTO.tokens.add(Formatter.toObject(jedis.getByString(id), Token.class)));

        return ResponseSchema.of(Formatter.toObject(Formatter.toJSON(_userDTO),UserDTO.class),HttpStatus.OK,"get");
    }

    @CrossOrigin
    @PutMapping(value = "/auth",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<AuthUser>  authUser(@RequestBody AuthUser auth) throws  IOException{
        if(DefaultPassWordAuth.login(auth.getSecurityId(),auth.getPassword())){
            return ResponseSchema.of(null,HttpStatus.FOUND,"{\"status\":\"success\",\"message\":\"Authenticated\"}");
        }
        return ResponseSchema.of(null,HttpStatus.NOT_FOUND,"{\"status\":\"failure\",\"message\":\"User or Security ID mismatch\"}");
    }

    @CrossOrigin
    @PutMapping(value = "/addAsset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<User> addNewAsset(@RequestParam  String securityId, @RequestBody Map<String,String> asset) throws IOException {
        String userDetails = redis.getByString(securityId);
        if(userDetails == null){
            return  ResponseSchema.of(new User(),HttpStatus.NOT_FOUND,"userNotFound");
        }
        User user = Formatter.convertToObject(userDetails, User.class);
        user.tokenIds =  (user.tokenIds != null) ? user.tokenIds : new HashSet<>();
        user.assetIds =  (user.assetIds != null) ? user.assetIds : new HashSet<>();
        String id =  asset.get("assetId");
        if(redis.getByString(id) == null){
            return ResponseSchema.of(user,HttpStatus.NOT_FOUND,"Asset Not exist :(");
        }
        if(user.assetIds.contains(id)){
            return ResponseSchema.of(user,HttpStatus.NOT_FOUND,"exist_assetAdded");
        }
        user.assetIds.add(id);
        redis.setByString(Long.toString(user.securityId), Formatter.toJSON(user));
        return ResponseSchema.of(Formatter.toObject(redis.getByString(Long.toString(user.securityId)), User.class),HttpStatus.OK,"assetAdded");
    }

    @CrossOrigin
    @PutMapping (value = "/addToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseSchema<User> addNewToken(@RequestParam  String securityId, @RequestBody Map<String,String> token) throws JsonProcessingException {
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(securityId);
        String id = token.get("tokenId");
        if(userDetails == null ){
            return  ResponseSchema.of(null,HttpStatus.NOT_FOUND,"User  Not exist :(");

        }
        User user = Formatter.convertToObject(userDetails, User.class);
        user.tokenIds =  (user.tokenIds != null) ? user.tokenIds : new HashSet<>();
        user.assetIds =  (user.assetIds != null) ? user.assetIds : new HashSet<>();
            if( jedis.getByString(id) == null){
            return  ResponseSchema.of(user,HttpStatus.NOT_FOUND,"User or Token Not exist :(");
        }
        user.tokenIds.add(id);
        jedis.setByString(Long.toString(user.securityId), Formatter.toJSON(user));
        return ResponseSchema.of( Formatter.toObject(redis.getByString(Long.toString(user.securityId)), User.class),HttpStatus.OK,"tokenAdded");
    }

    @CrossOrigin @PutMapping(value = "/removeAsset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<User> removeAsset(@RequestParam  String securityId, @RequestBody Map<String,String> asset) throws IOException{
        IJedis jedis = new JedisImx();
        String userDetails = jedis.getByString(securityId);
        User user = Formatter.convertToObject(userDetails, User.class);
        String id =  asset.get("assetId");
        if(user.assetIds.contains(id)){
            user.assetIds.remove(id);
            jedis.setByString(Long.toString(user.securityId), Formatter.toJSON(user));
            return ResponseSchema.of(user,HttpStatus.ACCEPTED,"removeAsset");
        }
        return ResponseSchema.of(Formatter.toObject(redis.getByString(Long.toString(user.securityId)), User.class),HttpStatus.OK,"removeAsset");
    }
}
