package com.imc.service.impl;

import com.imc.entity.Employee;
import com.imc.enums.EmployeeStatus;
import com.imc.exception.EmployeeNotFoundException;
import com.imc.repository.EmployeeRepository;
import com.imc.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    @Override
    public Employee findById(Long id) {
        return getEmployee(id);
    }

    @Transactional
    @Override
    public void deleteEmployee(Long id) {
        Employee employee = getEmployee(id);
        employee.setStatus(EmployeeStatus.INACTIVE);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    private Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
