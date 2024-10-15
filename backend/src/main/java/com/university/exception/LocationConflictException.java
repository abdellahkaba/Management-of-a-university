package com.university.exception;

public class LocationConflictException extends RuntimeException{
    public LocationConflictException(String message){
        super(message);
    }
}
