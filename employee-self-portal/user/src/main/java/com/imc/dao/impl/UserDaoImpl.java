package com.imc.dao.impl;

import com.imc.dao.UserDao;
import com.imc.entity.User;
import com.imc.enums.UserStatus;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserDaoImpl implements UserDao  {

    private final EntityManager entityManager;


    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Optional<User> findUser(final Long userId, final Long organizationId, final UserStatus status) {

        StringBuilder jpql = new StringBuilder("FROM User user WHERE user.id = :userId AND user.organizationId =:orgId");
        if ( status != null ) {
            jpql.append(" AND user.status = :status");
        }

        var query = entityManager.createQuery(jpql.toString(), User.class)
                .setParameter("userId", userId)
                .setParameter("orgId", organizationId);

        if ( status != null ) {
            query.setParameter("status", status);
        }
        return Optional.ofNullable(query.getSingleResultOrNull());
    }

    @Override
    public Optional<User> findUserByIdOrganizationId(final Long userId, final Long organizationId) {

        return  findUser(userId,organizationId,null);
    }

    @Override
    public Optional<User> findActiveUserByIdOrganizationId(Long userId, Long organizationId) {
        return findUser(userId, organizationId, UserStatus.ACTIVE);
    }
}
