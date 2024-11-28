package com.immutable.request.assets;

public interface IAssetsHandler <T>{
    public  Object create(T asset);
    public  Object  update(T asset);
    public   Object  get(String Id);
    public  Object  delete(long Id);
}
