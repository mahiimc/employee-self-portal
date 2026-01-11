package com.imc.service;

import com.imc.entity.User;

public interface UserService {
    User createUser(User user);
    void deleteUser(Long userId);
    User findUserById(Long userId);
    User findByUserIdOrganizationId(Long userId, Long organizationId);
}
