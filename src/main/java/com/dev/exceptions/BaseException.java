package com.dev.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseException extends RuntimeException {

    protected String message;
    protected String customMessage;
    protected HttpStatus httpStatus;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
        this.message = message;
    }
}
