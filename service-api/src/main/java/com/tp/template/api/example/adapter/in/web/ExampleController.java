package com.tp.template.api.example.adapter.in.web;

import com.tp.template.api.example.adapter.in.web.dto.CreateExampleRequest;
import com.tp.template.api.example.adapter.in.web.dto.GetExampleResponse;
import com.tp.template.api.example.adapter.in.web.dto.UpdateExampleRequest;
import com.tp.template.api.example.adapter.in.web.mapper.ExampleWebMapper;
import com.tp.template.api.example.application.port.in.ExampleCommand;
import com.tp.template.api.example.application.port.in.ExampleQuery;
import com.tp.template.infrastructure.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleQuery exampleQuery;
    private final ExampleCommand exampleCommand;

    @GetMapping("/{id}")
    public ApiResponse<GetExampleResponse> get(@PathVariable long id) {
        return ApiResponse.success(GetExampleResponse.from(exampleQuery.get(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> create(@RequestBody @Validated CreateExampleRequest req) {
        return ApiResponse.success(exampleCommand.create(ExampleWebMapper.toDomain(req)));
    }

    @PatchMapping("/{id}")
    public ApiResponse<Void> updateMessage(@PathVariable long id, @RequestBody @Validated UpdateExampleRequest req) {
        exampleCommand.updateMessage(id, req.message());
        return ApiResponse.success();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable long id) {
        exampleCommand.delete(id);
        return ApiResponse.success();
    }
}
