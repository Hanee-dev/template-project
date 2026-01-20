package com.tp.template.infrastructure.enums;

import java.util.Optional;
import lombok.Getter;

@Getter
public enum ErrorType {
    E_알수없는_오류(ErrorCode.E0001, LanguageId.M_알수없는_오류),
    E_대상정보_없음(ErrorCode.E0002, LanguageId.M_대상정보_없음),
    E_필수_값_없음(ErrorCode.E0003, LanguageId.M_필수_값_없음),
    E_캐시설정_오류(ErrorCode.E0004, LanguageId.M_캐시설정_오류),
    E_JWT_인증_실패(ErrorCode.E0005, LanguageId.M_JWT_인증_실패),
    ;

    private final ErrorCode errorCode;
    private final LanguageId languageId;

    ErrorType(ErrorCode errorCode, LanguageId languageId) {
        this.errorCode = errorCode;
        this.languageId = languageId;
    }

    public static Optional<ErrorType> from(String name) {
        try {
            return Optional.of(ErrorType.valueOf(name));
        }catch(IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
