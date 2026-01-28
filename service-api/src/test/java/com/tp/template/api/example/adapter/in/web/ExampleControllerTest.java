package com.tp.template.api.example.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tp.template.api.example.adapter.in.web.dto.CreateExampleRequest;
import com.tp.template.api.example.adapter.in.web.dto.UpdateExampleRequest;
import com.tp.template.api.example.application.domain.Example;
import com.tp.template.api.example.application.port.in.ExampleCommand;
import com.tp.template.api.example.application.port.in.ExampleQuery;
import com.tp.template.infrastructure.dto.LoginToken;
import com.tp.template.infrastructure.util.JwtTokenUtils;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
class ExampleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ExampleQuery exampleQuery;

    @MockitoBean
    private ExampleCommand exampleCommand;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    private String token;

    @BeforeEach
    void setUp() {
        LoginToken loginToken = LoginToken.builder()
                .userId(1L)
                .username("testuser")
                .locale(Locale.KOREA)
                .build();
        token = jwtTokenUtils.generateToken(loginToken);
    }

    @Test
    void getExample() throws Exception {
        // given
        given(exampleQuery.get(1L)).willReturn(Example.builder()
                .message("test")
                .build());

        // when & then
        mvc.perform(get("/api/example/{id}", 1L).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print());

        verify(exampleQuery).get(1L);
    }

    @Test
    void createExample() throws Exception {
        // given
        CreateExampleRequest request = new CreateExampleRequest("example name");
        given(exampleCommand.create(any(Example.class))).willReturn(1L);

        // when & then
        mvc.perform(post("/api/example").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(exampleCommand).create(any(Example.class));
    }

    @Test
    void updateMessage() throws Exception {
        // given
        UpdateExampleRequest request = new UpdateExampleRequest("example name");
        given(exampleCommand.updateMessage(anyLong(), anyString())).willReturn(null);

        // when & then
        mvc.perform(patch("/api/example/{id}", 1L).header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print());

        verify(exampleCommand).updateMessage(anyLong(), anyString());
    }

    @Test
    void deleteExample() throws Exception {
        // given
        willDoNothing().given(exampleCommand)
                .delete(anyLong());

        // when & then
        mvc.perform(delete("/api/example/{id}", 1L).header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print());

        verify(exampleCommand).delete(anyLong());
    }
}
