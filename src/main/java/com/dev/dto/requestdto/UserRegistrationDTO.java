package com.dev.dto.requestdto;

import com.dev.utils.custom.annotations.EmailValidator;
import com.dev.utils.custom.annotations.NotEmptyOrNull;
import com.dev.utils.custom.annotations.UsernameValidator;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class UserRegistrationDTO {

    @NotEmptyOrNull(message = "Invalid name!!!")
    @Size(min = 4, max = 100, message = "Name should be between 4 to 100 characters")
    protected String name;

    @NotEmptyOrNull(message = "Invalid name!!!")
    @Size(min = 4, max = 100, message = "Name should be between 4 to 100 characters")
    @UsernameValidator(message = "Username not allow any special characters or space")
    protected String userName;

    @NotEmptyOrNull(message = "Invalid email!!!")
    @Size(max = 30, message = "Email should be not exceed 30 characters")
    @EmailValidator(message = "Invalid email!!!")
    protected String email;

    @NotEmptyOrNull(message = "Invalid password!!!")
    @Size(min = 6, max = 30, message = "Password should be between 6 to 30 characters")
    protected String password;

    private boolean isBlogger;

    public UserRegistrationDTO setName(String name) {
        this.name = name.trim();
        return this;
    }

    public UserRegistrationDTO setUserName(String userName) {
        this.userName = userName.trim();
        return this;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email.trim();
        return this;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
