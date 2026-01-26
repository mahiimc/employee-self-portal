package com.imc.repository;

import com.imc.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("FROM Department where organizationId =:organizationId")
    List<Department> findDepartmentsByOrganizationId(Long organizationId);
}
