package com.immutable.request.assets;

public class AssetDTO {
    private String userId;
    private String assetId;
    private Asset asset;

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getUserId() {
        return userId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
