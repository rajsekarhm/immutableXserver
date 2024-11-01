package com.immutable.request.assets;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/asset")
public class AssetDAOImpl implements IAssetsHandler<AssetDAO.Builder>{
    @Override @RequestMapping("/createAsset")
    public String createAssetType(@RequestBody AssetDAO.Builder asset) {
        IJedis redis = new JedisImx();
        redis.setByString(Long.toString(asset.assetId), Formatter.toJSON(asset));
        return "ok";
    }

    @Override @RequestMapping("/updateAsset")
    public String updateAssetType(@RequestBody AssetDAO.Builder asset) {
        IJedis redis = new JedisImx();
        redis.setByString(Long.toString(asset.assetId), Formatter.toJSON(asset));
        return "ok";
    }

    @Override @RequestMapping("/getAsset")
    public String getAssetType(@RequestParam long assetId) {
        IJedis jedis = new JedisImx();
        return jedis.getByString(Long.toString(assetId));
    }

    @Override @RequestMapping("/deleteAsset")
    public String deleteType(@RequestParam long assetId) {
        return "ok";
    }
}
