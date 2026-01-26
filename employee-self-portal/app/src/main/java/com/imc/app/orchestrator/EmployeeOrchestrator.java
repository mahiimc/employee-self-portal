package com.imc.app.orchestrator;


import com.imc.app.dto.CreateEmployeeRequest;
import com.imc.app.dto.EmployeeResponse;
import com.imc.dto.UserRequest;
import com.imc.entity.Employee;
import com.imc.entity.Organization;
import com.imc.entity.Profile;
import com.imc.entity.Role;
import com.imc.entity.User;
import com.imc.enums.EmployeeStatus;
import com.imc.mapper.UserMapper;
import com.imc.service.EmployeeService;
import com.imc.service.OrganizationService;
import com.imc.service.RoleService;
import com.imc.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Component
public class EmployeeOrchestrator {

    private final UserService userService;
    private final EmployeeService employeeService;
    private final OrganizationService organizationService;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public EmployeeOrchestrator(UserService userService, EmployeeService employeeService,
                                OrganizationService organizationService,RoleService roleService, UserMapper userMapper,
                                PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.organizationService = organizationService;
        this.roleService  = roleService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public EmployeeResponse onboardEmployee(CreateEmployeeRequest request) {

        Organization organization = organizationService.findOrganizationById(request.getOrganizationId());

        User user = userService.createUser(userMapper.toEntity(new UserRequest(
                request.getUsername(),
                request.getPassword(),
                request.getOrganizationId()), passwordEncoder));

        Role role = roleService.findRoleByName(request.getRoleCode());

        Employee employee  = new Employee();
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setDepartmentId(request.getDepartmentId());
        employee.setOrganizationId(organization.getId());
        employee.setRoleId(role.getId());
        employee.setUserId(user.getId());
        employee.setJoinedOn(LocalDate.now());

        Profile profile = new Profile();
        profile.setEmail(request.getUsername());
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setMobile(request.getMobile());

        employee.setProfile(profile);

        employeeService.createEmployee(employee);


        EmployeeResponse response = new EmployeeResponse();
        response.setEmployeeId(employee.getId());
        response.setUsername(user.getUsername());
        response.setRole(role.getName());
        response.setOrganizationId(employee.getOrganizationId());
        response.setUserId(user.getId());
        response.setStatus(employee.getStatus().name());

        return response;
    }
}
