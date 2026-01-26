package com.imc.service.impl;

import com.imc.entity.Organization;
import com.imc.enums.OrganizationStatus;
import com.imc.exception.OrganizationNotFoundException;
import com.imc.repository.OrganizationRepository;
import com.imc.service.OrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService  {

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
            this.organizationRepository = organizationRepository;
    }

    @Transactional
    @Override
    public Organization createOrganization(Organization organization) {
      return  organizationRepository.save(organization);
    }

    @Transactional
    @Override
    public Organization findOrganizationById(Long id) {
        Organization activeOrganization =  findOrganization(id);
        if ( OrganizationStatus.ACTIVE.equals(activeOrganization.getStatus())) {
            return activeOrganization;
        }
        throw new OrganizationNotFoundException(id);
    }

    @Transactional
    @Override
    public void deleteOrganization(Long id) {
        Organization organization =  findOrganization(id);
        organization.setStatus(OrganizationStatus.DELETED);
    }

    @Transactional
    @Override
    public List<Organization> findActiveOrganizations() {
        return organizationRepository.findActiveOrganization();
    }

    private Organization findOrganization(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(id));
    }
}
