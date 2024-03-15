package com.xavier.clients.service.exception;

public class ResouceNotFoundException extends RuntimeException{
    public ResouceNotFoundException(String msn){
        super(msn);
    }
}
