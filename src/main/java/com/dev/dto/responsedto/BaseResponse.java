package com.dev.dto.responsedto;

import lombok.Data;

@Data
public class BaseResponse<T> {
    protected boolean isSuccess;
}
