package com.imc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI employeePortalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Self Service Portal API")
                        .description("APIs for employee registration, profile management and access control")
                        .version("v1.0"))
                .addServersItem(new Server().url("http://localhost:8080"));
    }
}
