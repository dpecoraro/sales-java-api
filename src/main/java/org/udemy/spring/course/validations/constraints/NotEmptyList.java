package org.udemy.spring.course.validations.constraints;

import org.udemy.spring.course.validations.NotEmptyListValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

public class NotEmptyList implements ConstraintValidator<NotEmptyListValidation, List> {

    @Override
    public void initialize(NotEmptyListValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(list) && !list.isEmpty();
    }
}
