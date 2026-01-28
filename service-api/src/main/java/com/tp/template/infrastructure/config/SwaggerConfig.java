package com.tp.template.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI().components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(List.of(securityRequirement))
                .info(new Info().title("Template API")
                        .description("Template 프로젝트 API 문서")
                        .version("v1.0.0")
                        .contact(new Contact().name("개발팀")
                                .email("dev@example.com")))
                .servers(List.of(new Server().url("http://localhost:8080")
                        .description("로컬 서버")));
    }
}