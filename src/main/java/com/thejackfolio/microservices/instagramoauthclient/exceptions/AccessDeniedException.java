package com.thejackfolio.microservices.instagramoauthclient.exceptions;

public class AccessDeniedException extends Exception{

    public AccessDeniedException(String message){
        super(message);
    }
}
