package org.udemy.spring.course.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException() {
        super("Order was not found");
    }
}
