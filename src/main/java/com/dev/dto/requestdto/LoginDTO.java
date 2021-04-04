package com.dev.dto.requestdto;

import com.dev.utils.custom.annotations.NotEmptyOrNull;
import lombok.Data;

@Data
public class LoginDTO {

    @NotEmptyOrNull(message = "Username can not be null or empty")
    private String userName;

    @NotEmptyOrNull(message = "Password can not be null or empty")
    private String password;
}
