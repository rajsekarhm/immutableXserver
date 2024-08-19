package com.immutable.request.assets;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public class AssetDAOImpl implements IAssetsHandler<Asset.Builder>{
    @Override @RequestMapping("/createAsset")
    public String createAssetType(@RequestBody Asset.Builder asset) {

        return "ok";
    }

    @Override @RequestMapping("/getAsset")
    public String updateAssetType(@RequestBody Asset.Builder asset) {
        return "ok";
    }

    @Override @RequestMapping("/updateAsset")
    public String getAssetType(@RequestBody long assetId) {
        return "ok";
    }

    @Override @RequestMapping("/deleteAsset")
    public String deleteType(@RequestBody long assetId) {
        return "ok";
    }
}
