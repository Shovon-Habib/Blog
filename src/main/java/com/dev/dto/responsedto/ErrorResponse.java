package com.dev.dto.responsedto;

import com.dev.exceptions.BaseException;
import lombok.Data;

@Data
public class ErrorResponse extends BaseResponse {

    public String errorMessage;

    public ErrorResponse(String message) {
        this.errorMessage = message;
        new BaseException(message);
    }

    public ErrorResponse(BaseException exception) {
        this.isSuccess = false;
        this.errorMessage = exception.getMessage();
    }
}
