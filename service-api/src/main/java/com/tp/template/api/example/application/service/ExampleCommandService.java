package com.tp.template.api.example.application.service;

import com.tp.template.api.example.application.domain.Example;
import com.tp.template.api.example.application.port.in.ExampleCommand;
import com.tp.template.api.example.application.port.in.ExampleQuery;
import com.tp.template.api.example.application.port.out.ExamplePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExampleCommandService implements ExampleCommand {

    private final ExampleQuery exampleQuery;
    private final ExamplePort examplePort;

    @Override
    public long create(Example example) {
        Example domain = examplePort.save(example);
        return domain.id();
    }

    @Override
    public Example updateMessage(long id, String message) {
        Example example = exampleQuery.get(id);
        example.updateMessage(message);
        return examplePort.save(example);
    }

    @Override
    public void delete(long id) {
        examplePort.delete(id);
    }
}
