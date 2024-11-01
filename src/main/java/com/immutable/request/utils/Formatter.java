package com.immutable.request.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.StringWriter;

public class Formatter {
    static  ObjectMapper  mapper = new ObjectMapper();
    static StringWriter writer = new StringWriter();

    public static   <T> T convertToJson(String json, Class<T> classType) throws JsonProcessingException {
        return Formatter.mapper.readValue(json, classType);
    }

    public static String convertToObject(Object object) throws IOException {
        Formatter.mapper.writeValue(writer, object);
        return Formatter.writer.toString();
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
