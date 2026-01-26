package com.imc.mapper;


import com.imc.dto.OrganizationRequest;
import com.imc.dto.OrganizationResponse;
import com.imc.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface OrganizationMapper {

    @Mapping(target = "status",constant = "ACTIVE")
    @Mapping(target = "type", constant = "TENANT")
    @Mapping(target = "id", ignore = true)
    Organization toEntity(OrganizationRequest organizationRequest);

    OrganizationResponse toResponse(Organization organization);
}
