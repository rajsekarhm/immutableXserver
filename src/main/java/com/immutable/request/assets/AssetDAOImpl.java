package com.immutable.request.assets;

import com.dependencies.jedis.IJedis;
import com.dependencies.jedis.JedisImx;
import com.dependencies.utils.ResponseSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.immutable.request.accounts.user.UserDAO;
import com.immutable.request.utils.Formatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/asset") @CrossOrigin
public class AssetDAOImpl implements IAssetsHandler<AssetDAO>{
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private final IJedis redis;

    @Autowired
    public AssetDAOImpl(@Qualifier("jedisImx") IJedis redis) {
        this.redis = redis;
    }

    @Override @PostMapping(value = "/createAsset", produces = MediaType.APPLICATION_JSON_VALUE) @CrossOrigin
    public ResponseSchema<AssetDAO> create(@RequestBody AssetDAO asset) {
        redis.setByString(asset.getAssetId(),gson.toJson(asset));
        return  ResponseSchema.of(Formatter.toObject(redis.getByString(asset.getAssetId()),AssetDAO.class), HttpStatus.OK,"createAsset");
    }

    @Override @PutMapping(value = "/updateAsset", produces = MediaType.APPLICATION_JSON_VALUE)@CrossOrigin
    public ResponseSchema<AssetDAO> update(@RequestParam String assetId,@RequestBody AssetDAO asset) {
        redis.setByString(assetId,Formatter.toJSON(asset));
        return ResponseSchema.of(Formatter.toObject(redis.getByString(assetId),AssetDAO.class),HttpStatus.OK,"updateAsset");
    }

    @Override @GetMapping(value = "/getAsset", produces = MediaType.APPLICATION_JSON_VALUE)@CrossOrigin
    public ResponseSchema<AssetDAO> get(@RequestParam String assetId) {
        String assetDetails = redis.getByString(assetId);
        return  ResponseSchema.of(Formatter.toObject(assetDetails,AssetDAO.class),HttpStatus.OK
                ,"getAsset");
    }

    @Override @DeleteMapping(value = "/deleteAsset", produces = MediaType.APPLICATION_JSON_VALUE)@CrossOrigin
    public ResponseSchema<AssetDAO> delete(@RequestParam String assetId) {
        redis.setByString(assetId,null);
        return ResponseSchema.of(Formatter.toObject(null,AssetDAO.class),HttpStatus.OK
                ,"deleteAsset");
    }

    @CrossOrigin  @PutMapping(value = "/changeAssociateUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseSchema<AssetDAO> changeOwnerShip(@RequestParam String assetId,@RequestBody Map<String,String> user) throws JsonProcessingException {
        String userId = user.get("userId");
        UserDAO _user = Formatter.toObject(redis.getByString(userId),UserDAO.class);
        _user.assetIds.add(assetId);
        redis.setByString(userId,Formatter.toJSON(_user));
        AssetDAO asset = Formatter.convertToObject(redis.getByString(assetId), AssetDAO.class);
        asset.associatedUser = userId;
        redis.setByString(assetId, gson.toJson(asset));
        return ResponseSchema.of(asset,HttpStatus.OK,"changeAssociateUser");
    }
}

