package com.dev.exceptions;

public class InvalidParamValueException extends BaseException {

    public InvalidParamValueException() {
        this("Invalid parameter value!!!");
    }

    public InvalidParamValueException(String customMessage) {
        this(customMessage, customMessage);
    }

    public InvalidParamValueException(String message, String customMessage) {
        super(message);
        this.message = message;
        this.customMessage = customMessage;
    }
}
