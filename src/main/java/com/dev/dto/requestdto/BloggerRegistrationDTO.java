package com.dev.dto.requestdto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class BloggerRegistrationDTO extends UserRegistrationDTO{

    @Size(max = 1000, message = "Blogger-Bio can not exceed 10000 characters")
    private String bloggerBio;


}
