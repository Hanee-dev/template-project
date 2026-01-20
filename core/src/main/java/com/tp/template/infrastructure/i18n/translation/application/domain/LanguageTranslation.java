package com.tp.template.infrastructure.i18n.translation.application.domain;

import com.tp.template.infrastructure.enums.LanguageId;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record LanguageTranslation(
        Long id,
        LanguageId languageId,
        String ko,
        String en,
        String zh,
        LocalDateTime createAt,
        String createdBy,
        LocalDateTime updateAt,
        String updatedBy
) {

    public static LanguageTranslation notTranslation(LanguageId languageId) {
        return LanguageTranslation.builder()
                .languageId(languageId)
                .ko("미설정된 번역")
                .en("Non-translation")
                .zh("未设置的翻译")
                .build();
    }

    public String getTranslation(String locale) {
        return switch(locale) {
            case "en" -> en;
            case "zh" -> zh;
            default -> ko;
        };
    }
}
