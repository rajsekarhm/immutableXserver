package com.immutable.request.assets;

public interface IAssetsHandler <T>{
    public  T create(T asset);
    public  T  update(String id,T asset);
    public  T  get(String Id);
    public  Object  delete(String Id);
}
