package com.imc.service;

import com.imc.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee findById(Long id);
    void deleteEmployee(Long id);
    List<Employee> findAllEmployees();
}
