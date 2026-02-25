package com.tp.template.api.example.adapter.in.web;

import com.tp.template.api.example.adapter.in.web.dto.CreateExampleRequest;
import com.tp.template.api.example.adapter.in.web.dto.GetExampleResponse;
import com.tp.template.api.example.adapter.in.web.dto.SearchExampleRequest;
import com.tp.template.api.example.adapter.in.web.dto.SearchExampleResponse;
import com.tp.template.api.example.adapter.in.web.dto.UpdateExampleRequest;
import com.tp.template.api.example.adapter.in.web.mapper.ExampleWebMapper;
import com.tp.template.api.example.application.port.in.ExampleCommand;
import com.tp.template.api.example.application.port.in.ExampleQuery;
import com.tp.template.infrastructure.dto.CommonApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/examples")
@RequiredArgsConstructor
@Tag(name = "Example", description = "예제 API")
public class ExampleController {

    private final ExampleQuery exampleQuery;
    private final ExampleCommand exampleCommand;

    @Operation(summary = "예제 리스트 조회", description = "예제 리스트를 페이징 조회합니다. message 생략 시 전체 조회됩니다.")
    @GetMapping
    public CommonApiResponse<SearchExampleResponse> search(@ParameterObject @ModelAttribute @Validated SearchExampleRequest req) {
        return CommonApiResponse.success(SearchExampleResponse.from(exampleQuery.search(ExampleWebMapper.map(req))));
    }

    @Operation(summary = "예제 조회", description = "ID로 예제를 조회합니다")
    @GetMapping("/{id}")
    public CommonApiResponse<GetExampleResponse> get(@Parameter(description = "예제 ID", required = true, example = "1") @PathVariable long id) {
        return CommonApiResponse.success(GetExampleResponse.from(exampleQuery.get(id)));
    }

    @Operation(summary = "예제 생성", description = "새로운 예제를 생성합니다")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonApiResponse<Long> create(@RequestBody @Validated CreateExampleRequest req) {
        return CommonApiResponse.success(exampleCommand.create(ExampleWebMapper.toDomain(req)));
    }

    @Operation(summary = "예제 메시지 수정", description = "예제의 메시지를 수정합니다")
    @PatchMapping("/{id}")
    public CommonApiResponse<Void> updateMessage(@Parameter(description = "예제 ID", required = true, example = "1") @PathVariable long id, @RequestBody @Validated UpdateExampleRequest req) {
        exampleCommand.updateMessage(id, req.message());
        return CommonApiResponse.success();
    }

    @Operation(summary = "예제 삭제", description = "예제를 삭제합니다")
    @DeleteMapping("/{id}")
    public CommonApiResponse<Void> delete(@Parameter(description = "예제 ID", required = true, example = "1") @PathVariable long id) {
        exampleCommand.delete(id);
        return CommonApiResponse.success();
    }
}
