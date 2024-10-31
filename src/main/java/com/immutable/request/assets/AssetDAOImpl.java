package com.immutable.request.assets;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AssetDAOImpl implements IAssetsHandler<AssetDAO.Builder>{
    @Override @RequestMapping("/createAsset")
    public String createAssetType(@RequestBody AssetDAO.Builder asset) {

        return "ok";
    }

    @Override @RequestMapping("/getAsset")
    public String updateAssetType(@RequestBody AssetDAO.Builder asset) {
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
