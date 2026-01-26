package com.imc.exception;

public class EmployeeNotFoundException extends  BaseException {

    public EmployeeNotFoundException(Long employeeId) {
        super(String.format("Employee not found : %s", employeeId), "EMPLOYEE_NOT_FOUND");
    }
}
