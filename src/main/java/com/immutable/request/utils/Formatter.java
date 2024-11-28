package com.immutable.request.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.StringWriter;

public class Formatter {
    public static final ObjectMapper mapper = new ObjectMapper()  .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) // Ignore extra fields in JSON
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    static StringWriter writer = new StringWriter();

    public static   <T> T convertToObject(String json, Class<T> classType) throws JsonProcessingException {
        return Formatter.mapper.readValue(json, classType);
    }

    public  static  String toJSON(Object _object){
        Gson json = new Gson();
        return  json.toJson(_object);
    }

    public  static Object toObject(String _string,Class<Object> _classObject) {
        Gson json = new Gson();
        return  json.fromJson(_string,_classObject);
    }


}
