package com.imc.dto;

import com.imc.enums.UserStatus;

public record UserResponse(
        Long id,
        String username,
        UserStatus status,
        Long orgId
) {

}
