package com.dev.exceptions;

public class DBOperationExceptionHandler extends BaseException {

    public DBOperationExceptionHandler() {
        this("Database operation failed!!!");
    }

    public DBOperationExceptionHandler(String customMessage) {
        this(customMessage, customMessage);
    }

    public DBOperationExceptionHandler(String message, String customMessage) {
        super(message);
        this.message = message;
        this.customMessage = customMessage;
    }
}
