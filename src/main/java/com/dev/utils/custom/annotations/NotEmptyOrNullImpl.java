package com.dev.utils.custom.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class NotEmptyOrNullImpl implements ConstraintValidator<NotEmptyOrNull, Object> {

    @Override
    public void initialize(NotEmptyOrNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        if (object == null)
            return false;
        if (object instanceof String) {
            String inputStr = (String) object;
            if (inputStr.trim().length() == 0) {
                return false;
            }
        }
        if (object instanceof Collection) {
            Collection collection = (Collection) object;
            if (collection.size() == 0)
                return false;
        }
        return true;
    }
}
