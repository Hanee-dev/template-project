package com.tp.template.infrastructure.i18n.translation.adapter.out.persistence.repository;

import com.tp.template.entity.i18n.LanguageTranslationEntity;
import com.tp.template.infrastructure.enums.LanguageId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageTranslationRepository extends JpaRepository<LanguageTranslationEntity, Long> {

    Optional<LanguageTranslationEntity> findByLanguageId(LanguageId languageId);
}
