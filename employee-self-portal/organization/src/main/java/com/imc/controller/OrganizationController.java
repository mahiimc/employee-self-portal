package com.imc.controller;

import com.imc.dto.ApiResponse;
import com.imc.dto.OrganizationRequest;
import com.imc.dto.OrganizationResponse;
import com.imc.entity.Organization;
import com.imc.mapper.OrganizationMapper;
import com.imc.service.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orgs")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;

    public OrganizationController(OrganizationService organizationService, OrganizationMapper organizationMapper) {
        this.organizationService = organizationService;
        this.organizationMapper = organizationMapper;
    }

    @PostMapping
    public ApiResponse<OrganizationResponse> createOrganization(@Valid  @RequestBody OrganizationRequest request) {
        Organization organization =  organizationService.createOrganization(organizationMapper.toEntity(request));
        return ApiResponse.success(organizationMapper.toResponse(organization));
    }

    @GetMapping("/{orgId}")
    public ApiResponse<OrganizationResponse> findOrganization(@PathVariable  Long orgId) {
        return ApiResponse.success(
                organizationMapper.toResponse(organizationService.findOrganizationById(orgId))
        );
    }

    @GetMapping
    public ApiResponse<List<OrganizationResponse>> findActiveOrganizations(){

        List<OrganizationResponse> organizations = organizationService.findActiveOrganizations()
                .stream().map(organizationMapper :: toResponse)
                .toList();

        return ApiResponse.success(organizations);
    }

    @DeleteMapping("/{orgId}")
    public ApiResponse<Long> deleteOrganization(@PathVariable Long orgId) {
        organizationService.deleteOrganization(orgId);
        return ApiResponse.success(orgId);
    }
}
