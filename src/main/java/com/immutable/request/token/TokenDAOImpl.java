package com.immutable.request.token;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.request.assets.IAssetsHandler;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token") @CrossOrigin
public class TokenDAOImpl implements IAssetsHandler<TokenDAO> {
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();
    private  IJedis redis = new JedisImx();
    @Override
    @CrossOrigin @PostMapping("/createToken")
    public TokenDAO create(@RequestBody TokenDAO token) {
        redis.setByString(token.getTokenId(),gson.toJson(token));
        return token;
    }


    @Override
    @CrossOrigin @PutMapping("/updateToken")
    public TokenDAO update(@RequestParam String tokenId,@RequestBody TokenDAO token) {
        redis.setByString(tokenId,Formatter.toJSON(token));
        return Formatter.toObject(redis.getByString(tokenId),TokenDAO.class);
    }

    @Override
    @CrossOrigin @GetMapping("/getToken")
    public TokenDAO get(@RequestParam String tokenId) {
        return Formatter.toObject(Formatter.toJSON(redis.getByString(tokenId)),TokenDAO.class);
    }

    @Override
    @CrossOrigin @DeleteMapping("/deleteToken")
    public Object delete(@RequestParam  String tokenId) {
        redis.setByString(tokenId,null);
        return null;
    }
}
