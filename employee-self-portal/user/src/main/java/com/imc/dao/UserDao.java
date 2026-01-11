package com.imc.dao;

import com.imc.entity.User;
import com.imc.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserDao  {
    Optional<User> findUserByIdOrganizationId(final Long userId, final Long organizationId);
    Optional<User> findActiveUserByIdOrganizationId(final Long userId, final Long organizationId);
}
