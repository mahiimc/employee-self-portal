package com.imc.exception;

public class OrganizationNotFoundException extends BaseException {

    public OrganizationNotFoundException(Long orgId) {
        super(String.format("Organization not found : %s",orgId),"ORGANIZATION_NOT_FOUND");
    }
}
