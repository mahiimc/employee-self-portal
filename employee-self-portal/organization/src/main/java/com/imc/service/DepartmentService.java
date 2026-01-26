package com.imc.service;


import com.imc.entity.Department;
import com.imc.entity.Organization;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Department createDepartment(Department department);
    Department findDepartmentById(Long id);
    void deleteDepartment(Long id);
    List<Department> findAllDepartments();
    List<Department> findOrganizationWiseDepartments(Long orgId);
    Map<Organization,List<Department>> getOrganizationDepartmentMap();
}
