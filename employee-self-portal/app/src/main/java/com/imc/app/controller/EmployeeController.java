package com.imc.app.controller;

import com.imc.app.dto.CreateEmployeeRequest;
import com.imc.app.dto.EmployeeResponse;
import com.imc.app.orchestrator.EmployeeOrchestrator;
import com.imc.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeOrchestrator employeeOrchestrator;

    public EmployeeController(EmployeeOrchestrator employeeOrchestrator) {
        this.employeeOrchestrator = employeeOrchestrator;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<EmployeeResponse> createEmployee(@RequestBody  CreateEmployeeRequest request) {
        EmployeeResponse response = employeeOrchestrator.onboardEmployee(request);
        return ApiResponse.success(response);
    }
}
