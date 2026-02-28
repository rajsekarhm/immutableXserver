package com.immutable.request.assets;

import com.dependencies.utils.ResponseSchema;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface IAssetsHandler <T>{
    ResponseEntity<ResponseSchema<T>> create(T asset);
    ResponseEntity<ResponseSchema<T>> update(String id, T asset);
    ResponseEntity<? extends ResponseSchema<?>> get(String id) throws JsonProcessingException;
    ResponseEntity<ResponseSchema<T>> delete(String id);
}
