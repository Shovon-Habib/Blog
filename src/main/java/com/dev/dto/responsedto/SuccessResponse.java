package com.dev.dto.responsedto;

import lombok.Data;

@Data
public class SuccessResponse<T> extends BaseResponse<T> {

    public T replyMessage;

    public SuccessResponse() {
        this.isSuccess = true;
    }

    public SuccessResponse(T message) {
        this.isSuccess = true;
        this.replyMessage = message;
    }
}
