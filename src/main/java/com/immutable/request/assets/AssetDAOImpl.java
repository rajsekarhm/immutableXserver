package com.immutable.request.assets;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/asset") @CrossOrigin
public class AssetDAOImpl implements IAssetsHandler<AssetDAO>{
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .create();
    @Override @PostMapping("/createAsset")@CrossOrigin
    public Object create(@RequestBody AssetDAO asset) {
        IJedis redis = new JedisImx();
        redis.setByString(asset.getTokenId(),gson.toJson(asset));
        return asset;
    }

    @Override @PutMapping("/updateAsset")@CrossOrigin
    public String update(@RequestBody AssetDAO asset) {return "ok";
    }

    @Override @GetMapping("/getAsset")@CrossOrigin
    public Object get(@RequestParam String assetId) {
        IJedis jedis = new JedisImx();
        return Formatter.toJSON(jedis.getByString(assetId));
    }

    @Override @DeleteMapping("/deleteAsset")@CrossOrigin
    public String delete(@RequestParam long assetId) {
        return "ok";
    }
}
