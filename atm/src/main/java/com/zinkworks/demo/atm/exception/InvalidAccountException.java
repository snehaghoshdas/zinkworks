package com.zinkworks.demo.atm.exception;

public class InvalidAccountException extends RuntimeException{

    public InvalidAccountException(String message) {
        super(message);
    }
}
