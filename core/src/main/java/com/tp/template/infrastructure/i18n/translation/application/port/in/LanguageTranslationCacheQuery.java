package com.tp.template.infrastructure.i18n.translation.application.port.in;

import com.tp.template.infrastructure.enums.LanguageId;
import com.tp.template.infrastructure.i18n.translation.application.domain.LanguageTranslation;

public interface LanguageTranslationCacheQuery {

    LanguageTranslation getTranslation(LanguageId languageId);
}
