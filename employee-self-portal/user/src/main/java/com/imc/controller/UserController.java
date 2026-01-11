package com.imc.controller;

import com.imc.dto.ApiResponse;
import com.imc.dto.UserRequest;
import com.imc.dto.UserResponse;
import com.imc.entity.User;
import com.imc.mapper.UserMapper;
import com.imc.service.UserService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        User user = userService.createUser(userMapper.toEntity(userRequest, passwordEncoder));
        return ApiResponse.success(userMapper.toResponse(user));
    }

    @GetMapping("/{userId}")
    public UserResponse findUser(@PathVariable  Long userId) {
        return userMapper.toResponse(userService.findUserById(userId));
    }
}
