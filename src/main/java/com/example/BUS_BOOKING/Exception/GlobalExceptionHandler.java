package com.example.BUS_BOOKING.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger((GlobalExceptionHandler.class));

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,Object>> handleEnumException(HttpMessageNotReadableException e){
        Map<String,Object> response=new HashMap<>();
        response.put( "status" ,"ERROR");
        response.put ("message" , "Invalid Data or Payload");
        logger.error(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(BusAlreadyExistsException.class)
    public ResponseEntity <Map<String , Object>> busAlreadyExists(BusAlreadyExistsException e){

        Map<String,Object> response = new HashMap<>();
        response.put("status", "ERROR");
        response.put("message" ,e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
