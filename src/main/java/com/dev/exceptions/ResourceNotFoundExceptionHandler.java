package com.dev.exceptions;

public class ResourceNotFoundExceptionHandler extends BaseException {

    public ResourceNotFoundExceptionHandler() {
        this("Resource not found!!!");
    }

    public ResourceNotFoundExceptionHandler(String customMessage) {
        this(customMessage, customMessage);
    }

    public ResourceNotFoundExceptionHandler(String message, String customMessage) {
        super(message);
        this.message = message;
        this.customMessage = customMessage;
    }
}
