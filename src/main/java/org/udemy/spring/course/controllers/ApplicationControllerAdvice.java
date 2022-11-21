package org.udemy.spring.course.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.udemy.spring.course.exceptions.ApiErrors;
import org.udemy.spring.course.exceptions.OrderException;
import org.udemy.spring.course.exceptions.OrderNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(OrderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRulesExceptionErrors(OrderException ex) {
        String error = ex.getMessage();
        return new ApiErrors(error);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleOrderNotFoundException(OrderNotFoundException ex) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}
