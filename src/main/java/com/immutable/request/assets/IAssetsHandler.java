package com.immutable.request.assets;

public interface IAssetsHandler <T>{
    public  String createAssetType(T asset);
    public  String  updateAssetType(T asset);
    public   String  getAssetType(String Id);
    public  String  deleteType(long Id);
}
