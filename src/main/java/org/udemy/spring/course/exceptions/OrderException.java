package org.udemy.spring.course.exceptions;

public class OrderException extends RuntimeException {

    public OrderException(String message) {
        super(message);
    }
}
