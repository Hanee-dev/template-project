package com.tp.template.infrastructure.filter;

import com.tp.template.infrastructure.dto.CommonApiResponse;
import com.tp.template.infrastructure.dto.CustomUserPrincipal;
import com.tp.template.infrastructure.enums.ErrorType;
import com.tp.template.infrastructure.i18n.translation.application.domain.LanguageTranslation;
import com.tp.template.infrastructure.i18n.translation.application.port.in.LanguageTranslationCacheQuery;
import com.tp.template.infrastructure.util.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;
    private final ObjectMapper objectMapper;
    private final LanguageTranslationCacheQuery translationQuery;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/no-auth/") || path.startsWith("/swagger/") || path.startsWith("/actuator/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        // @formatter:off
        try {
            String token = extractToken(request);
            if(!StringUtils.hasText(token)) {
                sendErrorResponse(request, response, ErrorType.E_JWT_인증_실패);
                return;
            }

            CustomUserPrincipal principal = CustomUserPrincipal.from(jwtTokenUtils.parseToken(token));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }catch(Exception e) {
            sendErrorResponse(request, response, ErrorType.E_JWT_인증_실패);
        }
        // @formatter:on
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

    private void sendErrorResponse(HttpServletRequest request, HttpServletResponse response, ErrorType errorType) throws IOException {
        // @formatter:off
        CommonApiResponse<Void> body = CommonApiResponse.fail(errorType);

        LanguageTranslation translation = translationQuery.getTranslation(errorType.getLanguageId());
        String language = Optional.ofNullable(request.getHeader("Accept-Language")).orElse("ko");
        body.translate(translation.getTranslation(language));

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(body));
        // @formatter:on
    }
}