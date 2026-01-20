package com.tp.template.infrastructure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt.auth")
public record JwtAuthProperties(
        String secretKey,
        long expiredAtSecond
) {

}
