package com.tp.template.infrastructure.i18n.translation.application.service;

import com.tp.template.infrastructure.constants.CacheConstants;
import com.tp.template.infrastructure.enums.ErrorType;
import com.tp.template.infrastructure.exception.InitializerException;
import com.tp.template.infrastructure.i18n.translation.adapter.out.persistence.LanguageTranslationAdapter;
import com.tp.template.infrastructure.i18n.translation.application.domain.LanguageTranslation;
import com.tp.template.infrastructure.i18n.translation.application.port.in.LanguageTranslationCacheCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LanguageTranslationCacheCommandService implements LanguageTranslationCacheCommand {

    private final LanguageTranslationAdapter languageTranslationAdapter;
    private final CacheManager cacheManager;

    @Override
    public void loadAllCache() {
        // @formatter:off
        log.info("Loading all translations into cache");

        clearCache(); // 기존 캐시 초기화

        Cache cache = cacheManager.getCache(CacheConstants.LANGUAGE_TRANSLATIONS);
        if (cache == null) {
            throw new InitializerException(ErrorType.E_캐시설정_오류);
        }

        List<LanguageTranslation> translations = languageTranslationAdapter.all();

        translations.forEach(translation ->
                cache.put(translation.languageId(), translation)
        );

        log.info("Loaded {} translations into cache", translations.size());
        // @formatter:on
    }

    @Override
    @CacheEvict(value = CacheConstants.LANGUAGE_TRANSLATIONS, allEntries = true)
    public void clearCache() {
        log.info("Cleared all translations from cache");
    }
}
