package com.immutable.request.assets;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/token") @CrossOrigin
public class TokenDAOImpl implements  IAssetsHandler<TokenDAO>{
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();
    @Override
    @CrossOrigin @PostMapping("/createToken")
    public Object create(@RequestBody TokenDAO token) {
        IJedis redis = new JedisImx();
        redis.setByString(token.getTokenId(),gson.toJson(token));
        return token;
    }

    @Override
    @CrossOrigin @PutMapping("/updateToken")
    public Object update(@RequestBody TokenDAO asset) {
        return "";
    }

    @Override
    @CrossOrigin @GetMapping("/getToken")
    public Object get(@RequestParam String tokenId) {
        IJedis redis = new JedisImx();
        return Formatter.toJSON(redis.getByString(tokenId));
    }

    @Override
    @CrossOrigin @DeleteMapping("/deleteToken")
    public Object delete(@RequestParam long Id) {
        return "";
    }
}
