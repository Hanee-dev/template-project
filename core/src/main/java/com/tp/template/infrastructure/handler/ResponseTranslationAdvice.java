package com.tp.template.infrastructure.handler;

import com.tp.template.infrastructure.dto.ApiResponse;
import com.tp.template.infrastructure.i18n.translation.application.domain.LanguageTranslation;
import com.tp.template.infrastructure.i18n.translation.application.port.in.LanguageTranslationCacheQuery;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ResponseTranslationAdvice implements ResponseBodyAdvice<ApiResponse<?>> {

    private final LanguageTranslationCacheQuery translationQuery;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return ApiResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public ApiResponse<?> beforeBodyWrite(
            ApiResponse<?> body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        if(body == null || body.getLanguageId() == null) {
            return body;
        }

        LanguageTranslation translation = translationQuery.getTranslation(body.getLanguageId());

        Locale locale = LocaleContextHolder.getLocale();
        body.translate(translation.getTranslation(locale.getLanguage()));

        return body;
    }
}
