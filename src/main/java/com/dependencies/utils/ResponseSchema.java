package com.dependencies.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter
public class ResponseSchema<T>  {
    private HttpStatusCode status;
    private String message;
    private T data;
    private LocalTime timestamp;

    public ResponseSchema() {
        this.timestamp = LocalTime.from(LocalDateTime.now());
    }

    public ResponseSchema(HttpStatusCode status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = LocalTime.from(LocalDateTime.now());
    }

    public static <T> ResponseSchema<T> of(T data, HttpStatusCode status, String message) {
        return new ResponseSchema<>(status, message, data);
    }

    /**
     * Wraps this ResponseSchema into a ResponseEntity with the correct HTTP status code.
     */
    public ResponseEntity<ResponseSchema<T>> toResponseEntity() {
        return ResponseEntity.status(this.status).body(this);
    }

    /**
     * Creates a ResponseEntity with the correct HTTP status code directly.
     */
    public static <T> ResponseEntity<ResponseSchema<T>> respond(T data, HttpStatus status, String message) {
        ResponseSchema<T> schema = new ResponseSchema<>(status, message, data);
        return ResponseEntity.status(status).body(schema);
    }

}