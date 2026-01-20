package com.tp.template.entity.i18n;

import com.tp.template.entity.common.BaseEntity;
import com.tp.template.infrastructure.enums.LanguageId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@SuperBuilder
@Table(name = "language_translation", comment = "번역", indexes = {@Index(name = "idx_language_translation_language_id", columnList = "language_id")})
public class LanguageTranslationEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "language_id", comment = "메시지", columnDefinition = "varchar(50)", nullable = false, unique = true)
    private LanguageId languageId;

    @Column(comment = "한국어", columnDefinition = "varchar(500)")
    private String ko;

    @Column(comment = "영어", columnDefinition = "varchar(500)")
    private String en;

    @Column(comment = "중국어", columnDefinition = "varchar(500)")
    private String zh;
}
