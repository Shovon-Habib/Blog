package com.dev.utils.custom.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

import static com.dev.utils.Utils.USERNAME_CHARS_REGEX;

public class UsernameValidatorImpl implements ConstraintValidator<UsernameValidator, String> {


    @Override
    public void initialize(UsernameValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

        if (username == null || username.isEmpty())
            return false;
        return !Pattern.compile(USERNAME_CHARS_REGEX).matcher(username).find();
    }
}
