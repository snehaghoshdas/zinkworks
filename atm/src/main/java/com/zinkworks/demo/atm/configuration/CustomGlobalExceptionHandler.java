package com.zinkworks.demo.atm.configuration;

import com.zinkworks.demo.atm.exception.IncorrectPinException;
import com.zinkworks.demo.atm.exception.InvalidAccountException;
import com.zinkworks.demo.atm.exception.InvalidUserException;
import com.zinkworks.demo.atm.exception.NotEnoughBalanceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
@Log4j2
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(InvalidUserException.class)
    public void springHandleNotFound(HttpServletResponse response) throws IOException {
        log.error(response.getStatus()+"Invalid User");
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(InvalidAccountException.class)
    public void springHandleInvalidAccount(HttpServletResponse response) throws IOException {
        log.error(response.getStatus()+"Invalid Account");
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
    }

    @ExceptionHandler(IncorrectPinException.class)
    public void springHandleIncorrectPinAccount(HttpServletResponse response) throws IOException {
        log.error(response.getStatus()+"IncorrectPin");
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    public void springHandleNotEnoughBalance(HttpServletResponse response) throws IOException {
        log.error(response.getStatus()+"NotEnoughBalance");
        response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
    }

}
