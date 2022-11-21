package org.udemy.spring.course.exceptions;

import lombok.Getter;

import java.util.List;

public class ApiErrors {
    @Getter
    private List<String> errors;

    public ApiErrors(String errorMessage) {
        this.errors.add(errorMessage);
    }

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }
}
