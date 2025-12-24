package com.example.BUS_BOOKING.Exception;

public class BusAlreadyExistsException extends RuntimeException{
    public BusAlreadyExistsException(String message){
        super(message);
    }
}
