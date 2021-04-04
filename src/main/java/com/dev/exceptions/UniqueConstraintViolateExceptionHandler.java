package com.dev.exceptions;

public class UniqueConstraintViolateExceptionHandler extends BaseException {

    public UniqueConstraintViolateExceptionHandler() {
        this("Duplicate entry!! Unique constraint violated.");
    }

    public UniqueConstraintViolateExceptionHandler(String customMessage) {
        this(customMessage, customMessage);
    }

    public UniqueConstraintViolateExceptionHandler(String message, String customMessage) {
        super(message);
        this.message = message;
        this.customMessage = customMessage;
    }
}
