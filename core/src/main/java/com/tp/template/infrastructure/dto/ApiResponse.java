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
public class ApiResponse<T> {

    ApiResponseCode result;
    ErrorCode errorCode;
    LanguageId languageId;
    String message;
    T data;

    public static ApiResponse<Void> fail(ErrorType error) {
        // @formatter:off
        return ApiResponse.<Void>builder()
                .result(ApiResponseCode.FM)
                .errorCode(error.getErrorCode())
                .languageId(error.getLanguageId())
                .build();
        // @formatter:on
    }

    public static <T> ApiResponse<T> fail(ErrorType error, T data) {
        // @formatter:off
        return ApiResponse.<T>builder()
                .result(ApiResponseCode.FM)
                .errorCode(error.getErrorCode())
                .languageId(error.getLanguageId())
                .data(data)
                .build();
        // @formatter:on
    }

    public static ApiResponse<Void> success() {
        // @formatter:off
        return ApiResponse.<Void>builder()
                .result(ApiResponseCode.SM)
                .languageId(LanguageId.M_정상처리_완료)
                .build();
        // @formatter:on
    }

    public static <T> ApiResponse<T> success(T data) {
        // @formatter:off
        return ApiResponse.<T>builder()
                .result(ApiResponseCode.SM)
                .languageId(LanguageId.M_정상처리_완료)
                .data(data)
                .build();
        // @formatter:on
    }

    public void translate(String message) {
        this.message = message;
    }
}
