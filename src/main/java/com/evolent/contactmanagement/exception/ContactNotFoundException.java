package com.evolent.contactmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends Exception {
    public ContactNotFoundException(String message) {
        super(message);
    }
    public ContactNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
