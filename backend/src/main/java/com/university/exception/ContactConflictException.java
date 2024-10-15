package com.university.exception;

public class ContactConflictException extends RuntimeException{
    public ContactConflictException(String message){
        super(message);
    }
}
