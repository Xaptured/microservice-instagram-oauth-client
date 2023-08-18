package com.thejackfolio.microservices.instagramoauthclient.exceptions;

public class AccessTokenException extends Exception{

    public AccessTokenException(String message){
        super(message);
    }
}
