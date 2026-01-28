package com.tp.template.infrastructure.util;

import com.tp.template.infrastructure.dto.LoginToken;
import com.tp.template.infrastructure.properties.JwtAuthProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenUtils {

    // @formatter:off
    private final JwtAuthProperties jwtAuth;
    private final Key key;
    private static final String USER_ID = "userId";
    private static final String USER_NAME = "username";
    private static final String LOCALE = "locale";

    public JwtTokenUtils(JwtAuthProperties jwtAuthProperties) {
        jwtAuth = jwtAuthProperties;
        key = Keys.hmacShaKeyFor(jwtAuthProperties.secretKey().getBytes());
    }

    public String generateToken(LoginToken loginToken) {
        return Jwts.builder()
                .claim(USER_ID, loginToken.userId())
                .claim(USER_NAME, loginToken.username())
                .claim(LOCALE, loginToken.locale().toLanguageTag())
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(jwtAuth.expiredAtSecond()).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public LoginToken parseToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new LoginToken(claims.get(USER_ID, Long.class), claims.get(USER_NAME, String.class), Locale.forLanguageTag(claims.get(LOCALE, String.class)));
    }
    // @formatter:on
}
