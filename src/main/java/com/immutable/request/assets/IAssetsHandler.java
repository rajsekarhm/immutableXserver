package com.immutable.request.assets;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IAssetsHandler <T>{
    public  Object create(T asset);
    public  Object  update(String id,T asset);
    public  Object  get(String Id) throws JsonProcessingException;
    public  Object  delete(String Id);
}
