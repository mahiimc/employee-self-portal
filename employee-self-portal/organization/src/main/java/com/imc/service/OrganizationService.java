package com.imc.service;

import com.imc.entity.Organization;

import java.util.List;

public interface OrganizationService {

    Organization createOrganization(Organization organization);

    Organization findOrganizationById(Long id);

    void deleteOrganization(Long id);

    List<Organization> findActiveOrganizations();
}
