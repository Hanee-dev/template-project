package com.tp.template.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Constraint;
import java.util.List;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

@Configuration
public class SwaggerConfig {

    private static final String VALIDATION_PACKAGE = "com.tp.template.infrastructure.validation";

    @PostConstruct
    public void ignoreCustomConstraintAnnotations() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata()
                        .isAnnotation();
            }
        };
        scanner.addIncludeFilter(new AnnotationTypeFilter(Constraint.class));

        Class<?>[] annotationClasses = scanner.findCandidateComponents(VALIDATION_PACKAGE)
                .stream()
                .map(bd -> {
                    try {
                        return Class.forName(bd.getBeanClassName());
                    }catch(ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(cls -> cls != null && cls.isAnnotation())
                .toArray(Class[]::new);

        if(annotationClasses.length > 0) {
            SpringDocUtils.getConfig()
                    .addAnnotationsToIgnore(annotationClasses);
        }
    }

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