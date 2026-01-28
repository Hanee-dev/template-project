package com.tp.template.infrastructure.dto;


import com.tp.template.infrastructure.enums.ApiResponseCode;
import com.tp.template.infrastructure.enums.ErrorCode;
import com.tp.template.infrastructure.enums.ErrorType;
import com.tp.template.infrastructure.enums.LanguageId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CommonApiResponse<T> {

    ApiResponseCode code;
    ErrorCode errorCode;
    LanguageId languageId;
    String message;
    T payload;

    public static CommonApiResponse<Void> fail(ErrorType error) {
        // @formatter:off
        return CommonApiResponse.<Void>builder()
                .code(ApiResponseCode.FM)
                .errorCode(error.getErrorCode())
                .languageId(error.getLanguageId())
                .build();
        // @formatter:on
    }

    public static <T> CommonApiResponse<T> fail(ErrorType error, T payload) {
        // @formatter:off
        return CommonApiResponse.<T>builder()
                .code(ApiResponseCode.FM)
                .errorCode(error.getErrorCode())
                .languageId(error.getLanguageId())
                .payload(payload)
                .build();
        // @formatter:on
    }

    public static CommonApiResponse<Void> success() {
        // @formatter:off
        return CommonApiResponse.<Void>builder()
                .code(ApiResponseCode.SM)
                .languageId(LanguageId.M_정상처리_완료)
                .build();
        // @formatter:on
    }

    public static <T> CommonApiResponse<T> success(T payload) {
        // @formatter:off
        return CommonApiResponse.<T>builder()
                .code(ApiResponseCode.SM)
                .languageId(LanguageId.M_정상처리_완료)
                .payload(payload)
                .build();
        // @formatter:on
    }

    public void translate(String message) {
        this.message = message;
    }
}
