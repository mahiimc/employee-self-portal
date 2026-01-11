package com.imc.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest (

        @NotBlank(message = "{user.username.notBlank}")
        @Size(min=3, max = 100, message = "{user.username.size}")
        String username,

        @NotBlank(message = "{user.password.notBlank}")
        @Size(min = 8, max = 72, message = "{user.password.size}")
        String password,

        @NotNull(message = "{user.orgId.notNull}")
        Long orgId
) {
}
