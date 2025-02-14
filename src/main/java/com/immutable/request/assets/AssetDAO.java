package com.immutable.request.assets;

import com.dependencies.jedis.IJedis;
import com.dependencies.utils.ResponseSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.request.accounts.user.User;
import com.immutable.request.utils.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/asset") @CrossOrigin
public class AssetDAO implements IAssetsHandler<Asset>{
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private final IJedis redis;

    @Autowired
    public AssetDAO(@Qualifier("jedisImx") IJedis redis) {
        this.redis = redis;
    }

    @Override @PostMapping(value = "/createAsset", produces = MediaType.APPLICATION_JSON_VALUE) @CrossOrigin
    public ResponseSchema<Asset> create(@RequestBody Asset asset) {
        redis.setByString(asset.getAssetId(),gson.toJson(asset));
        return  ResponseSchema.of(Formatter.toObject(redis.getByString(asset.getAssetId()), Asset.class), HttpStatus.OK,"createAsset");
    }

    @Override @PutMapping(value = "/updateAsset", produces = MediaType.APPLICATION_JSON_VALUE)@CrossOrigin
    public ResponseSchema<Asset> update(@RequestParam String assetId, @RequestBody Asset asset) {
        redis.setByString(assetId,Formatter.toJSON(asset));
        return ResponseSchema.of(Formatter.toObject(redis.getByString(assetId), Asset.class),HttpStatus.OK,"updateAsset");
    }

    @Override @GetMapping(value = "/getAsset", produces = MediaType.APPLICATION_JSON_VALUE)@CrossOrigin
    public ResponseSchema<AssetDTO> get(@RequestParam String assetId) {
        String assetDetails = redis.getByString(assetId);
        AssetDTO  assetDTO = new AssetDTO();
        Asset asset = Formatter.toObject(assetDetails,Asset.class);
        assetDTO.setAsset(asset);
        assetDTO.setAssetId(assetId);
        assetDTO.setUserId(asset.getAssociatedUser());
        return  ResponseSchema.of(assetDTO,HttpStatus.OK
                ,"getAsset");
    }

    @Override @DeleteMapping(value = "/deleteAsset", produces = MediaType.APPLICATION_JSON_VALUE)@CrossOrigin
    public ResponseSchema<Asset> delete(@RequestParam String assetId) {
        redis.setByString(assetId,null);
        return ResponseSchema.of(Formatter.toObject(null, Asset.class),HttpStatus.OK
                ,"deleteAsset");
    }

    @CrossOrigin  @PutMapping(value = "/changeAssociateUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseSchema<Asset> changeOwnerShip(@RequestParam String assetId, @RequestBody Map<String,String> user) throws JsonProcessingException {
        String userId = user.get("userId");
        User _user = Formatter.toObject(redis.getByString(userId), User.class);
        _user.assetIds.add(assetId);
        redis.setByString(userId,Formatter.toJSON(_user));
        Asset asset = Formatter.convertToObject(redis.getByString(assetId), Asset.class);
        asset.associatedUser = userId;
        redis.setByString(assetId, gson.toJson(asset));
        return ResponseSchema.of(asset,HttpStatus.OK,"changeAssociateUser");
    }
}

