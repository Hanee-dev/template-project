package com.tp.template.infrastructure.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.tp.template.infrastructure.config.CoreTestConfig;
import com.tp.template.infrastructure.dto.LoginToken;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.StringUtils;

@SpringBootTest(classes = CoreTestConfig.class)
@ComponentScan(basePackages = "com.tp.template")
@ActiveProfiles("local")
class JwtTokenUtilsTest {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Test
    void generateToken() {
        LoginToken testToken = LoginToken.builder()
                .userId(1L)
                .username("testuser")
                .locale(Locale.KOREA)
                .build();
        String jwtToken = jwtTokenUtils.generateToken(testToken);

        assertThat(StringUtils.hasText(jwtToken)).isTrue();
    }

    @Test
    void parseToken() {
        LoginToken testToken = LoginToken.builder()
                .userId(1L)
                .username("testuser")
                .locale(Locale.KOREA)
                .build();

        String jwtToken = jwtTokenUtils.generateToken(testToken);
        LoginToken loginToken = jwtTokenUtils.parseToken(jwtToken);

        assertThat(loginToken).isNotNull();
    }
}