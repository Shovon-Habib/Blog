package com.dev.dto.requestdto;

import com.dev.utils.custom.annotations.NotEmptyOrNull;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserStatusDTO {

    @NotNull(message = "Invalid user ID!!")
    private UUID userId;
    @NotNull(message = "Invalid status!!")
    private Boolean status;
}
