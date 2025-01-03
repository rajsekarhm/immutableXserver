package com.immutable.request.token;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.dependencies.utils.ResponseSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.request.assets.IAssetsHandler;
import com.immutable.request.utils.Formatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token") @CrossOrigin
public class TokenDAOImpl implements IAssetsHandler<TokenDAO> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private  IJedis redis = new JedisImx();

    @Override
    @CrossOrigin @PostMapping(value = "/createToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<TokenDAO> create(@RequestBody TokenDAO token) {
        redis.setByString(token.getTokenId(),gson.toJson(token));
        return ResponseSchema.of(token, HttpStatus.OK,"createToken");
    }


    @Override
    @CrossOrigin @PutMapping(value = "/updateToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<TokenDAO> update(@RequestParam String tokenId,@RequestBody TokenDAO token) {
        redis.setByString(tokenId,Formatter.toJSON(token));
        return ResponseSchema.of(Formatter.toObject(redis.getByString(tokenId),TokenDAO.class),HttpStatus.OK,"updateToken");
    }

    @Override
    @CrossOrigin @GetMapping(value = "/getToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<TokenDAO> get(@RequestParam String tokenId) {
        return ResponseSchema.of(Formatter.toObject(redis.getByString(tokenId),TokenDAO.class),HttpStatus.OK,"getToken");
    }

    @Override
    @CrossOrigin @DeleteMapping(value = "/deleteToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseSchema<TokenDAO> delete(@RequestParam  String tokenId) {
        redis.setByString(tokenId,null);
        return ResponseSchema.of(null,HttpStatus.OK,"deleteToken");
    }
}
