package com.tp.template.api.example.adapter.in.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tp.template.api.example.adapter.in.web.dto.CreateExampleRequest;
import com.tp.template.api.example.adapter.in.web.dto.UpdateExampleRequest;
import com.tp.template.api.example.application.domain.Example;
import com.tp.template.api.example.application.port.in.ExampleCommand;
import com.tp.template.api.example.application.port.in.ExampleQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.restdocs.test.autoconfigure.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
class ExampleControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ExampleQuery exampleQuery;

    @MockitoBean
    private ExampleCommand exampleCommand;

    // @formatter:off
    @Test
    void getExample() throws Exception {
        // given
        given(exampleQuery.get(1L)).willReturn(Example.builder()
                .message("test")
                .build());

        mvc.perform(get("/example/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-example",
                        pathParameters(
                                parameterWithName("id").description("id 값")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("공통 응답 코드"),
                                fieldWithPath("errorCode").type(JsonFieldType.STRING).optional().description("에러 코드"),
                                fieldWithPath("languageId").type(JsonFieldType.STRING).description("응답 메시지 id"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data.message").type(JsonFieldType.STRING).description("예시 메시지")
                        )));

        verify(exampleQuery).get(1L);
    }

    @Test
    void createExample() throws Exception {
        // given
        CreateExampleRequest request = new CreateExampleRequest("example name");
        given(exampleCommand.create(any(Example.class))).willReturn(1L);

        // when
        mvc.perform(post("/example").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("create-example",
                        requestFields(
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지")
                        ),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("공통 응답 코드"),
                                fieldWithPath("errorCode").type(JsonFieldType.STRING).optional().description("에러 코드"),
                                fieldWithPath("languageId").type(JsonFieldType.STRING).description("응답 메시지 id"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data").type(JsonFieldType.NUMBER).description("키 값 반환")
                        )));

        // then
        verify(exampleCommand).create(any(Example.class));
    }

    @Test
    void updateMessage() throws Exception {
        // given
        UpdateExampleRequest request = new UpdateExampleRequest("example name");
        given(exampleCommand.updateMessage(any(Long.class), anyString())).willReturn(null);

        // when
        mvc.perform(patch("/example/{id}", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("update-example",
                        pathParameters(
                                parameterWithName("id").description("id 값")),
                        requestFields(
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지")),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("공통 응답 코드"),
                                fieldWithPath("errorCode").type(JsonFieldType.STRING).optional().description("에러 코드"),
                                fieldWithPath("languageId").type(JsonFieldType.STRING).description("응답 메시지 id"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL).description("NULL 값 반환")
                        )));

        // then
        verify(exampleCommand).updateMessage(any(Long.class), anyString());
    }

    @Test
    void deleteExample() throws Exception {
        // given
        willDoNothing().given(exampleCommand).delete(anyLong());

        // when
        mvc.perform(delete("/example/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("delete-example",
                        pathParameters(
                                parameterWithName("id").description("id 값")),
                        responseFields(
                                fieldWithPath("result").type(JsonFieldType.STRING).description("공통 응답 코드"),
                                fieldWithPath("errorCode").type(JsonFieldType.STRING).optional().description("에러 코드"),
                                fieldWithPath("languageId").type(JsonFieldType.STRING).description("응답 메시지 id"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                                fieldWithPath("data").type(JsonFieldType.NULL).description("NULL 값 반환")
                        )));

        // then
        verify(exampleCommand).delete(any(Long.class));
    }
    // @formatter:on
}
