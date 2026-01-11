package com.imc.controller;

import com.imc.dto.ApiResponse;
import com.imc.dto.UserRequest;
import com.imc.dto.UserResponse;
import com.imc.entity.User;
import com.imc.mapper.UserMapper;
import com.imc.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private  final UserService userService;
    private  final UserMapper userMapper;
    private  final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Operation(summary = "Create user",
            description = "Creates a new user in the organization")

    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "User created successfully",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "User already exists")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.createUser(userMapper.toEntity(userRequest, passwordEncoder));
        return ApiResponse.success(userMapper.toResponse(user));
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> findUser(@PathVariable  Long userId) {
        return ApiResponse.success(userMapper.toResponse(userService.findUserById(userId)));
    }
}
