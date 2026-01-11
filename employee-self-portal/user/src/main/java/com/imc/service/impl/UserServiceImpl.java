package com.imc.service.impl;

import com.imc.entity.User;
import com.imc.enums.UserStatus;
import com.imc.exception.DuplicateUserException;
import com.imc.exception.UserNotFoundException;
import com.imc.repository.UserRepository;
import com.imc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User createUser(final User user) {
        try {
            return userRepository.save(user);
        }
        catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof org.hibernate.exception.ConstraintViolationException cve) {
                String sqlState = cve.getSQLState();
                if (sqlState.equals("23505")) {
                    throw new DuplicateUserException(user.getUsername());
                }
            }
            throw ex;
        }
    }

    @Transactional
    @Override
    public void deleteUser(final Long userId) {
       User user =  getExistingUser(userId);
       user.setStatus(UserStatus.DELETED);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(final Long userId) {
        return getExistingUser(userId);
    }

    /**
     * To get the organization specific user
     *
     * @param userId
     * @param organizationId
     * @return
     */
    @Override
    public User findByUserIdOrganizationId(final Long userId, final Long organizationId) {
        return userRepository.findUserByIdOrganizationId(userId,organizationId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private User getExistingUser(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
