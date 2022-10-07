package com.zinkworks.demo.atm.exception;

public class IncorrectDenominationException extends RuntimeException{
    public IncorrectDenominationException(String message) {
        super(message);
    }
}
