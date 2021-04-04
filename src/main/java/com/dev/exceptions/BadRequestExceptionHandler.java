package com.dev.exceptions;


public class BadRequestExceptionHandler extends BaseException {

    public BadRequestExceptionHandler() {
        this("Bad Request from Client");
    }

    public BadRequestExceptionHandler(String customMessage) {
        this(customMessage, customMessage);
    }

    public BadRequestExceptionHandler(String message, String customMessage) {
        super(message);
        this.message = message;
        this.customMessage = customMessage;
    }
}
