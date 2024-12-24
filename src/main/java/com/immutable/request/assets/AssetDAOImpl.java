package com.immutable.request.assets;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.request.utils.Formatter;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/asset") @CrossOrigin
public class AssetDAOImpl implements IAssetsHandler<AssetDAO>{
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private IJedis redis = new JedisImx();

    @Override @PostMapping("/createAsset")@CrossOrigin
    public AssetDAO create(@RequestBody AssetDAO asset) {
        if(redis.exists(asset.getAssetId())){
            return  null;
        }
        redis.setByString(asset.getAssetId(),gson.toJson(asset));
        return  Formatter.toObject(redis.getByString(asset.getAssetId()),AssetDAO.class);
    }

    @Override @PutMapping("/updateAsset")@CrossOrigin
    public AssetDAO update(@RequestParam String assetId,@RequestBody AssetDAO asset) {
        redis.setByString(assetId,Formatter.toJSON(asset));
        return Formatter.toObject(redis.getByString(assetId),AssetDAO.class);
    }

    @Override @GetMapping("/getAsset")@CrossOrigin
    public AssetDAO get(@RequestParam String assetId) {
        String assetDetails = redis.getByString(assetId);
        return Formatter.toObject(assetDetails,AssetDAO.class);
    }

    @Override @DeleteMapping("/deleteAsset")@CrossOrigin
    public Object delete(@RequestParam String assetId) {
        redis.setByString(assetId,null);
        return null;
    }

    @CrossOrigin  @PutMapping("/changeAssociateUser")
    public  String changeOwnerShip(@RequestParam String assetId,@RequestBody Map<String,String> user) throws JsonProcessingException {
        String userId = user.get("userId");
        AssetDAO asset = Formatter.convertToObject(redis.getByString(assetId), AssetDAO.class);
        asset.associatedUser = userId;
        redis.setByString(assetId, gson.toJson(asset));
        return assetId;
    }
}

