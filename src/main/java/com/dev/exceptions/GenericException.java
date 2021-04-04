package com.dev.exceptions;

public class GenericException extends BaseException {

    public GenericException() {
        this("Internal Error Occurred");
    }

    public GenericException(String customMessage) {
        this(customMessage, customMessage);
    }

    public GenericException(String message, String customMessage) {
        super(message);
        this.message = message;
        this.customMessage = customMessage;
    }
}
