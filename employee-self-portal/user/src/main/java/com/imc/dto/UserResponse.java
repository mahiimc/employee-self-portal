package com.imc.dto;

import com.imc.enums.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User response")
public record UserResponse(
        @Schema(example = "10")
        Long id,

        @Schema(example = "john")
        String username,

        @Schema(example = "ACTIVE")
        UserStatus status,

        @Schema(example = "1")
        Long orgId
) {

}
