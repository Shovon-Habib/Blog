package com.dev.utils.custom.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyOrNullImpl.class)
@Documented
public @interface NotEmptyOrNull {

    String message() default "Property should not null or empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
