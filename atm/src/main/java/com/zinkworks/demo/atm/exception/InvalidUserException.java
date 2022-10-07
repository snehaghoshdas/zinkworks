package com.zinkworks.demo.atm.exception;

public class InvalidUserException  extends RuntimeException{
    public InvalidUserException(String userId) {
        super("Invalid User ID :"+userId);

    }
}
