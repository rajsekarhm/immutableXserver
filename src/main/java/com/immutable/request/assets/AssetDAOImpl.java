package com.immutable.request.assets;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/asset")@CrossOrigin
public class AssetDAOImpl implements IAssetsHandler<AssetDAO.Builder>{
    @Override @PostMapping("/createAsset")@CrossOrigin
    public String createAssetType(@RequestBody AssetDAO.Builder asset) {
        IJedis redis = new JedisImx();
        redis.setByString(Long.toString(asset.assetId), Formatter.toJSON(asset));
        return "ok";
    }

    @Override @PutMapping("/updateAsset")@CrossOrigin
    public String updateAssetType(@RequestBody AssetDAO.Builder asset) {
        IJedis redis = new JedisImx();
        redis.setByString(Long.toString(asset.assetId), Formatter.toJSON(asset));
        return "ok";
    }

    @Override @GetMapping("/getAsset")@CrossOrigin
    public String getAssetType(@RequestParam long assetId) {
        IJedis jedis = new JedisImx();
        return jedis.getByString(Long.toString(assetId));
    }

    @Override @DeleteMapping("/deleteAsset")@CrossOrigin
    public String deleteType(@RequestParam long assetId) {
        return "ok";
    }
}
