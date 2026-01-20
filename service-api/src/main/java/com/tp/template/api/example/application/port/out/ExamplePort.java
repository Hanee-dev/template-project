package com.tp.template.api.example.application.port.out;

import com.tp.template.api.example.application.domain.Example;

public interface ExamplePort {

    Example get(long id);

    Example save(Example example);

    void delete(long id);
}
