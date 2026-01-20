package com.tp.template.infrastructure.i18n.translation.adapter.in.initializer;

import com.tp.template.infrastructure.i18n.translation.application.port.in.LanguageTranslationCacheCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TranslationCacheInitializer {

    private final LanguageTranslationCacheCommand languageTranslationCacheCommand;

    @EventListener(ApplicationReadyEvent.class)
    public void initializeTranslationCache() {
        log.info("Initializing translation cache...");

        try {
            languageTranslationCacheCommand.loadAllCache();
            log.info("Translation cache initialized successfully");
        }catch(Exception e) {
            log.error("Failed to initialize translation cache", e);
        }
    }
}
