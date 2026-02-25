package com.tp.template.api.example.adapter.in.web.dto;

import com.tp.template.infrastructure.dto.BaseSearchRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchExampleRequest extends BaseSearchRequest {

    @Schema(description = "메시지 검색어 (부분 일치, 생략 시 전체 조회)", example = "테스트")
    private String message;
}
