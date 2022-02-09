package com.study.boot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.study.boot.controller.sample.ExampleController;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ExampleController.class})
@WebMvcTest
class ExmpleControllerTest {
    
    @Autowired
    private MockMvc mock;
    
    @DisplayName("1. /hello에 get요청시 hello world가 응답하는지 확인한다.")
    @Test
    void hello1() throws Exception {
        MvcResult result = mock.perform(get("/hello").contentType(MediaType.TEXT_HTML))
                               .andExpect(status().isOk())
                               .andReturn();
        
        String text = result.getResponse().getContentAsString();
        assertEquals("hello world", text);
    }

    @Test
    void hello2() throws Exception {
        mock.perform(get("/hello")).andExpect(content().string("hello world"));
    }
    
    @Test
    void exmple() throws Exception {
        mock.perform(get("/example").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("num").value(10))
            .andExpect(jsonPath("text").value("ten"));

    }
}
