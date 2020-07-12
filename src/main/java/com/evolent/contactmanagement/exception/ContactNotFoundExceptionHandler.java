package com.evolent.contactmanagement.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class ContactNotFoundExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public ModelAndView handleContactNotFoundException(HttpServletRequest request, ContactNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("timestamp", new Date());
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.addObject("exception", ex);
        modelAndView.addObject("path", request.getRequestURI());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
