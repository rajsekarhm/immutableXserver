package com.immutable.request.assets;

public class AssetDTO {
    String userId;
    String AssetId;
    Asset asset;

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getAssetId() {
        return AssetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setAssetId(String assetId) {
        AssetId = assetId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
