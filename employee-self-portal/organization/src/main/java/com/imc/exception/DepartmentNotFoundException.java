package com.imc.exception;

public class DepartmentNotFoundException extends BaseException {

    public DepartmentNotFoundException(Long deptId) {
        super(String.format("Department not found : %s",deptId),"DEPT_NOT_FOUND");
    }
}
