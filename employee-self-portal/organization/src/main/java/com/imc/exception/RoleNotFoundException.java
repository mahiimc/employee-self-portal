package com.imc.exception;

public class RoleNotFoundException extends BaseException {

    public RoleNotFoundException(Long roleId) {
        super(String.format("Role not found : %s",roleId),"ROLE_NOT_FOUND");
    }
    public  RoleNotFoundException(String name) {
        super(String.format("Role not found :%s ", name), "ROLE_NOT_FOUND");
    }
}
