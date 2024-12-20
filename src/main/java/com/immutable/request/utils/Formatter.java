package com.immutable.request.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class Formatter {
    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) // Ignore extra fields in JSON
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    private static final Gson gson = new Gson();

    public static   <T> T convertToObject(String json, Class<T> classType) throws JsonProcessingException {
        return Formatter.mapper.readValue(json, classType);
    }

    public  static  String toJSON(Object _object){
        return  gson.toJson(_object);
    }

    public static <T> T toObject(String jsonString, Class<T> classType) {
        return gson.fromJson(jsonString, classType);
    }

}
