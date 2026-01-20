package com.tp.template.infrastructure.i18n.translation.adapter.out.persistence;

import com.tp.template.entity.i18n.LanguageTranslationEntity;
import com.tp.template.infrastructure.enums.LanguageId;
import com.tp.template.infrastructure.i18n.translation.adapter.out.persistence.mapper.LanguageTranslationPersistenceMapper;
import com.tp.template.infrastructure.i18n.translation.adapter.out.persistence.repository.LanguageTranslationRepository;
import com.tp.template.infrastructure.i18n.translation.application.domain.LanguageTranslation;
import com.tp.template.infrastructure.i18n.translation.application.port.out.LanguageTranslationPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LanguageTranslationAdapter implements LanguageTranslationPort {

    private final LanguageTranslationRepository languageTranslationRepository;

    @Override
    public List<LanguageTranslation> all() {
        return languageTranslationRepository.findAll()
                .stream()
                .map(LanguageTranslationPersistenceMapper::fromEntity)
                .toList();
    }

    @Override
    public LanguageTranslation get(LanguageId languageId) {
        return languageTranslationRepository.findByLanguageId(languageId)
                .map(LanguageTranslationPersistenceMapper::fromEntity)
                .orElse(LanguageTranslation.notTranslation(languageId));
    }

    @Override
    public LanguageTranslation save(LanguageTranslation translation) {
        LanguageTranslationEntity entity = languageTranslationRepository.save(LanguageTranslationPersistenceMapper.toEntity(translation));
        return LanguageTranslationPersistenceMapper.fromEntity(entity);
    }
}
