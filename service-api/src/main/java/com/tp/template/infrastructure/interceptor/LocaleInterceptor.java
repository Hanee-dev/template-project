package com.tp.template.infrastructure.interceptor;

import com.tp.template.infrastructure.dto.CustomUserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LocaleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Locale locale = determineLocale(request);
        LocaleContextHolder.setLocale(locale);
        return true;
    }

    private Locale determineLocale(HttpServletRequest request) {
        // 1순위: Accept-Language 헤더
        String headerLocale = request.getHeader("Accept-Language");
        if(StringUtils.hasText(headerLocale)) {
            try {
                return Locale.forLanguageTag(headerLocale);
            }catch(Exception e) {
                log.warn("Invalid Accept-Language: {}", headerLocale);
            }
        }

        // 2순위: JWT 토큰의 locale (SecurityContext에서)
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if(auth != null && auth.isAuthenticated() && auth.getPrincipal() instanceof CustomUserPrincipal principal) {
            if(principal.getLocale() != null) {
                return principal.getLocale();
            }
        }

        // 3순위: 한국어
        return Locale.KOREA;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LocaleContextHolder.resetLocaleContext();
    }
}