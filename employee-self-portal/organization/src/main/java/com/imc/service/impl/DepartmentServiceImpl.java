package com.imc.service.impl;

import com.imc.entity.Department;
import com.imc.entity.Organization;
import com.imc.enums.DepartmentStatus;
import com.imc.exception.DepartmentNotFoundException;
import com.imc.repository.DepartmentRepository;
import com.imc.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService  {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Transactional
    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    @Override
    public Department findDepartmentById(Long id) {
        return getDepartment(id);
    }

    @Transactional
    @Override
    public void deleteDepartment(Long id) {
        Department dept  = getDepartment(id);
        dept.setStatus(DepartmentStatus.INACTIVE);
    }

    @Transactional
    @Override
    public List<Department> findAllDepartments() {
        return  departmentRepository.findAll();
    }

    @Transactional
    @Override
    public List<Department> findOrganizationWiseDepartments(Long orgId) {
        return departmentRepository.findDepartmentsByOrganizationId(orgId);
    }

    @Transactional
    @Override
    public Map<Organization, List<Department>> getOrganizationDepartmentMap() {
        return Map.of();
    }

    private Department getDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }
}
