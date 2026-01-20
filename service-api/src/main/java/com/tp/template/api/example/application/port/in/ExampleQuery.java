package com.tp.template.api.example.application.port.in;

import com.tp.template.api.example.application.domain.Example;

public interface ExampleQuery {

    Example get(long id);
}
