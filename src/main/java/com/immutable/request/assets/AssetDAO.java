package com.immutable.request.assets;

import com.dependencies.jedis.IJedis;
import com.dependencies.utils.ResponseSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.immutable.request.accounts.user.User;
import com.immutable.request.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/asset")
@CrossOrigin
public class AssetDAO implements IAssetsHandler<Asset> {
    private final IJedis redis;

    @Autowired
    public AssetDAO(@Qualifier("jedisImx") IJedis redis) {
        this.redis = redis;
    }

    @Override
    @PostMapping(value = "/createasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Asset>> create(@RequestBody Asset asset) {
        if (asset.getAssetId() == null || asset.getAssetId().isBlank()) {
            return ResponseSchema.respond(null, HttpStatus.BAD_REQUEST, "assetId is required");
        }
        redis.setByString(asset.getAssetId(), Formatter.toJSON(asset));
        Asset created = Formatter.toObject(redis.getByString(asset.getAssetId()), Asset.class);
        return ResponseSchema.respond(created, HttpStatus.CREATED, "createAsset");
    }

    @Override
    @PutMapping(value = "/updateasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Asset>> update(@RequestParam String assetId, @RequestBody Asset asset) {
        String existing = redis.getByString(assetId);
        if (existing == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "Asset not found");
        }
        redis.setByString(assetId, Formatter.toJSON(asset));
        Asset updated = Formatter.toObject(redis.getByString(assetId), Asset.class);
        return ResponseSchema.respond(updated, HttpStatus.OK, "updateAsset");
    }

    @Override
    @GetMapping(value = "/getasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<AssetDTO>> get(@RequestParam String assetId) {
        String assetDetails = redis.getByString(assetId);
        if (assetDetails == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "Asset not found");
        }
        Asset asset = Formatter.toObject(assetDetails, Asset.class);
        AssetDTO assetDTO = new AssetDTO();
        assetDTO.setAsset(asset);
        assetDTO.setAssetId(assetId);
        assetDTO.setUserId(asset.getAssociatedUser());
        return ResponseSchema.respond(assetDTO, HttpStatus.OK, "getAsset");
    }

    @Override
    @DeleteMapping(value = "/deleteasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Asset>> delete(@RequestParam String assetId) {
        String existing = redis.getByString(assetId);
        if (existing == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "Asset not found");
        }
        redis.del(assetId);
        return ResponseSchema.respond(null, HttpStatus.OK, "deleteAsset");
    }

    @PutMapping(value = "/changeassociateUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSchema<Asset>> changeOwnerShip(@RequestParam String assetId, @RequestBody Map<String, String> user) throws JsonProcessingException {
        String userId = user.get("userId");
        if (userId == null || userId.isBlank()) {
            return ResponseSchema.respond(null, HttpStatus.BAD_REQUEST, "userId is required");
        }

        String userJson = redis.getByString(userId);
        if (userJson == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "User not found");
        }

        String assetJson = redis.getByString(assetId);
        if (assetJson == null) {
            return ResponseSchema.respond(null, HttpStatus.NOT_FOUND, "Asset not found");
        }

        User _user = Formatter.convertToObject(userJson, User.class);
        _user.assetIds = (_user.assetIds != null) ? _user.assetIds : new HashSet<>();
        _user.assetIds.add(assetId);
        redis.setByString(userId, Formatter.toJSON(_user));

        Asset asset = Formatter.convertToObject(assetJson, Asset.class);
        asset.setAssociatedUser(userId);
        redis.setByString(assetId, Formatter.toJSON(asset));

        return ResponseSchema.respond(asset, HttpStatus.OK, "changeAssociateUser");
    }
}

