package com.imc.mapper;

import com.imc.dto.UserRequest;
import com.imc.dto.UserResponse;
import com.imc.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    @Mapping(source = "orgId", target = "organizationId")
    @Mapping(target = "passwordHash", expression = "java(passwordEncoder.encode(request.password()))")
    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequest request, @Context PasswordEncoder passwordEncoder);

    @Mapping(source = "organizationId", target = "orgId")
    UserResponse toResponse(User user);
}

