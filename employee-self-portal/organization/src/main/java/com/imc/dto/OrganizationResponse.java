package com.imc.dto;

import com.imc.enums.OrganizationStatus;
import com.imc.enums.OrganizationType;

public record OrganizationResponse (
        Long id,
        String name,
        OrganizationStatus status,
        OrganizationType type
){
}
