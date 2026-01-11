package com.imc.repository;

import com.imc.dao.UserDao;
import com.imc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>, UserDao  {
}
