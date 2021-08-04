package com.example.test_task.controller;

import com.example.test_task.entity.Words;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@EnableWebMvc
@SpringBootTest
class TestTaskTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test1() throws Exception {
        Words inputWords = new Words(new String[]{"fish", "horse", "egg", "goose", "eagle"});
        Words outputWords = new Words(new String[]{"fish", "horse", "egg", "goose", "eagle"});

        forTest(inputWords, outputWords);
    }

    @Test
    public void test2() throws Exception {
        Words inputWords = new Words(new String[]{"fish", "horse", "snake", "goose", "eagle"});
        Words outputWords = new Words(new String[]{"fish", "horse"});

        forTest(inputWords, outputWords);
    }

    @Test
    public void test3() throws Exception {
        Words inputWords = new Words(new String[]{"fish", "horse", "", "goose", "eagle"});
        Words outputWords = new Words(new String[]{"fish", "horse"});

        forTest(inputWords, outputWords);
    }

    @Test
    public void test4() throws Exception {
        Words inputWords = new Words(new String[]{"", "horse", "", "goose", "eagle"});
        Words outputWords = new Words(new String[]{});

        forTest(inputWords, outputWords);
    }

    public void forTest(Words inputWords, Words outputWords) throws Exception {
        String inputJson = objectMapper.writeValueAsString(inputWords);
        String outputJson = objectMapper.writeValueAsString(outputWords);

        MvcResult mvcResult = mockMvc.perform(post("/words/game")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseMvcResult = mvcResult.getResponse().getContentAsString();
        log.info(responseMvcResult);
        Assertions.assertEquals(outputJson, responseMvcResult);
    }
}