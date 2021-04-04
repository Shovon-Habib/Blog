package com.dev.dto.requestdto;

import com.dev.utils.custom.annotations.NotEmptyOrNull;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class BlogPostRequestDto {

    @NotEmptyOrNull(message = "Blog Title can not be empty or null")
    @Size(max = 500, message = "Title should be less than 500 characters")
    private String title;

    @NotEmptyOrNull(message = "Blog content can not be empty or null")
    private String content;
}
