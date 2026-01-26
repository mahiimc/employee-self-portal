package com.imc.service.impl;

import com.imc.entity.Role;
import com.imc.exception.RoleNotFoundException;
import com.imc.repository.RoleRepository;
import com.imc.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository  = roleRepository;
    }

    @Transactional
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role findRoleByName(String name) {
        return getRole(name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> findAllRoles(Long ordId) {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteRole(Long id) {
        Role role = getRole(id);
        role.setActive(false);
    }


    private Role getRole(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RoleNotFoundException(name));
    }

    private Role getRole(Long id) {
        return roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
    }
}
