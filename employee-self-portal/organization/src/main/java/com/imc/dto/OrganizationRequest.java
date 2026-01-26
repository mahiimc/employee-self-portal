package com.imc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OrganizationRequest(
        @NotBlank(message = "{org.name.notBlank}")
        @Size(min = 3, max = 150, message = "{org.name.size}")
        String name
) {
}
