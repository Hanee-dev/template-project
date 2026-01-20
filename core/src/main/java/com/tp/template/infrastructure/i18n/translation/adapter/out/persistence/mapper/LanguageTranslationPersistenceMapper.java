package com.tp.template.infrastructure.i18n.translation.adapter.out.persistence.mapper;

import com.tp.template.entity.i18n.LanguageTranslationEntity;
import com.tp.template.infrastructure.i18n.translation.application.domain.LanguageTranslation;

public class LanguageTranslationPersistenceMapper {

    public static LanguageTranslation fromEntity(LanguageTranslationEntity entity) {
        return LanguageTranslation.builder()
                .id(entity.getId())
                .languageId(entity.getLanguageId())
                .ko(entity.getKo())
                .en(entity.getEn())
                .zh(entity.getZh())
                .createAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .updateAt(entity.getUpdatedAt())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public static LanguageTranslationEntity toEntity(LanguageTranslation domain) {
        return LanguageTranslationEntity.builder()
                .id(domain.id())
                .languageId(domain.languageId())
                .ko(domain.ko())
                .en(domain.en())
                .zh(domain.zh())
                .build();
    }
}
