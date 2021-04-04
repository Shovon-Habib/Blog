package com.dev.utils.custom.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidatorImpl.class)
@Documented
public @interface UsernameValidator {

    String message() default "Invalid username!!!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
