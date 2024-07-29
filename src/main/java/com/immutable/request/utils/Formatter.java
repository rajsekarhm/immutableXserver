package com.immutable.request.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.immutable.request.RequestDemo;

import java.io.IOException;
import java.io.StringWriter;

public class Formatter {
    ObjectMapper mapper;
    StringWriter writer;

    public Formatter() {
        this.mapper = new ObjectMapper();
        this.writer = new StringWriter();
    }

    public <T> T convertToJson(String json, Class<T> classType) throws JsonProcessingException {
        return this.mapper.readValue(json, classType);
    }

    public String convertToObject(Object object) throws IOException {
        this.mapper.writeValue(writer, object);
        return this.writer.toString();
    }
}
