package org.udemy.spring.course.validations;

import org.udemy.spring.course.validations.constraints.NotEmptyList;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyList.class)
public @interface NotEmptyListValidation {
    String message() default "List must not be empty";
}


