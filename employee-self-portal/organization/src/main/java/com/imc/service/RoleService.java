package com.imc.service;

import com.imc.entity.Role;

import java.util.List;

public interface RoleService {

    Role createRole(Role role);
    Role findRoleByName(String name);
    List<Role> findAllRoles(Long ordId);
    void deleteRole(Long id);
}
