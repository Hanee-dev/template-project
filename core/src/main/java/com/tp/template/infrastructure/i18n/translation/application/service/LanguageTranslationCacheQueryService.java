package com.tp.template.infrastructure.i18n.translation.application.service;

import com.tp.template.infrastructure.constants.CacheConstants;
import com.tp.template.infrastructure.enums.LanguageId;
import com.tp.template.infrastructure.i18n.translation.adapter.out.persistence.LanguageTranslationAdapter;
import com.tp.template.infrastructure.i18n.translation.application.domain.LanguageTranslation;
import com.tp.template.infrastructure.i18n.translation.application.port.in.LanguageTranslationCacheQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LanguageTranslationCacheQueryService implements LanguageTranslationCacheQuery {

    private final LanguageTranslationAdapter languageTranslationAdapter;

    @Override
    @Cacheable(value = CacheConstants.LANGUAGE_TRANSLATIONS, key = "#languageId")
    public LanguageTranslation getTranslation(LanguageId languageId) {
        return languageTranslationAdapter.get(languageId);
    }
}
