package com.filrouge.gypsogest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResponseHandler {
    public int statusCode;
    public String timeStamp;
    public String message;
    public Object data;

    public ResponseHandler(int statusCode, String message, Object data){
        this.statusCode = statusCode;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"));
        this.message = message;
        this.data = data;
    }

    public ResponseHandler (int statusCode, String message){
        this.statusCode = statusCode;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss"));
        this.message = message;
    }

    public static ResponseEntity ok(Object data, String message){
        return new ResponseEntity<>(new ResponseHandler(HttpStatus.OK.value(), message, data), HttpStatus.OK);
    }
    public static ResponseEntity created(Object data, String message){
        return new ResponseEntity<>(new ResponseHandler(HttpStatus.CREATED.value(), message, data), HttpStatus.CREATED);
    }
    public static ResponseEntity notFound(String message){
        return new ResponseEntity<>(new ResponseHandler(HttpStatus.NOT_FOUND.value(), message), HttpStatus.NOT_FOUND);
    }
    public static ResponseEntity badRequest(String message){
        return new ResponseEntity<>(new ResponseHandler(HttpStatus.BAD_REQUEST.value(), message), HttpStatus.BAD_REQUEST);
    }
}
