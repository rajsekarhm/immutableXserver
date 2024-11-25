package com.immutable.request.assets;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/asset") @CrossOrigin
public class AssetDAOImpl implements IAssetsHandler<AssetDAO>{
    @Override @PostMapping("/createAsset")@CrossOrigin
    public String createAssetType(@RequestBody AssetDAO asset) {
        IJedis redis = new JedisImx();
        System.out.println(Formatter.toJSON(asset));
        redis.setByString(asset.tokenId, Formatter.toJSON(asset));
        return "ok";
    }

    @Override @PutMapping("/updateAsset")@CrossOrigin
    public String updateAssetType(@RequestBody AssetDAO asset) {
        IJedis redis = new JedisImx();
        redis.setByString(asset.tokenId, Formatter.toJSON(asset));
        return "ok";
    }

    @Override @GetMapping("/getAsset")@CrossOrigin
    public String getAssetType(@RequestParam String tokenId) {
        IJedis jedis = new JedisImx();
        return jedis.getByString(tokenId);
    }

    @Override @DeleteMapping("/deleteAsset")@CrossOrigin
    public String deleteType(@RequestParam long assetId) {
        return "ok";
    }
}
