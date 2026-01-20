package com.tp.template.infrastructure.i18n.translation.application.port.out;

import com.tp.template.infrastructure.enums.LanguageId;
import com.tp.template.infrastructure.i18n.translation.application.domain.LanguageTranslation;
import java.util.List;

public interface LanguageTranslationPort {

    List<LanguageTranslation> all();

    LanguageTranslation get(LanguageId languageId);

    LanguageTranslation save(LanguageTranslation translation);
}
