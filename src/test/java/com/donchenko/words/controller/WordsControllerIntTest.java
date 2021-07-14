package com.donchenko.words.controller;

import com.donchenko.words.WordsArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class WordsControllerIntTest {

    @Autowired
    private MockMvc mvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private RequestBuilder getRequest(WordsArray words) {
        return MockMvcRequestBuilders.post("/api/words")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(words));
    }

    @Test
    void getValidWordsForFirstCase() throws Exception {
        WordsArray words = new WordsArray();
        words.setWords(new String[]{"fish", "horse", "egg", "goose", "eagle"});

        String expected = "{\"words\":[\"fish\",\"horse\",\"egg\",\"goose\",\"eagle\"]}";

        MvcResult result = mvc.perform(getRequest(words)).andReturn();
        assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    void getValidWordsForSecondCase() throws Exception {
        WordsArray words = new WordsArray();
        words.setWords(new String[]{"fish", "horse", "snake", "goose", "eagle"});

        String expected = "{\"words\":[\"fish\",\"horse\"]}";

        MvcResult result = mvc.perform(getRequest(words)).andReturn();
        assertEquals(expected, result.getResponse().getContentAsString());
    }



    @Test
    void getValidWordsForThirdCase() throws Exception {
        WordsArray words = new WordsArray();
        words.setWords(new String[]{"fish", "horse", "", "goose", "eagle"});

        String expected = "{\"words\":[\"fish\",\"horse\"]}";

        MvcResult result = mvc.perform(getRequest(words)).andReturn();
        assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    void getValidWordsForFourthCase() throws Exception {
        WordsArray words = new WordsArray();
        words.setWords(new String[]{"", "horse", "", "goose", "eagle"});

        String expected = "{\"words\":[]}";

        MvcResult result = mvc.perform(getRequest(words)).andReturn();
        assertEquals(expected, result.getResponse().getContentAsString());
    }
}