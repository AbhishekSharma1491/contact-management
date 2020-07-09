package com.evolent.contactmanagement.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ContactNotFoundExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public @ResponseBody String handleContactNotFoundException(ContactNotFoundException ex) {
       return ex.getMessage();
    }
}
