package com.dev.exceptions;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ExceptionUtils {

    public List<String> dataRangeExceedExceptionMessageProvider(Throwable throwable) {

        List<String> errorMessages = new ArrayList<>(1);
        try {
            if (throwable instanceof ConstraintViolationException) {
                Set<ConstraintViolation<?>> set = ((ConstraintViolationException) throwable).getConstraintViolations();
                set.forEach(elem -> {
                    errorMessages.add(elem.getConstraintDescriptor().getMessageTemplate());
                });
            }
        } catch (ClassCastException e) {
            errorMessages.add("Internal Error Occurred");
        }
        return errorMessages;
    }

    public String javaxValidationMessageParser(ConstraintViolationException ex) {

        Set<ConstraintViolation<?>> messageSet = ex.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation constraintViolation : messageSet) {
            stringBuilder.append("Message: ");
            stringBuilder.append(constraintViolation.getMessage());
            stringBuilder.append(", Class: ");
            stringBuilder.append(constraintViolation.getRootBeanClass().getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
