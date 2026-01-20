package com.tp.template.api.example.application.port.in;

import com.tp.template.api.example.application.domain.Example;

public interface ExampleCommand {

    long create(Example example);

    Example updateMessage(long id, String message);

    void delete(long id);
}
