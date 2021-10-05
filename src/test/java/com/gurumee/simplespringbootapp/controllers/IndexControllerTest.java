package com.gurumee.simplespringbootapp.controllers;

import com.gurumee.simplespringbootapp.apis.services.GenerateRandomNumberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(IndexController.class)
class IndexControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenerateRandomNumberService generateRandomNumberService;

    @Test
    @DisplayName("GET / 테스트 - 2xx")
    public void get_index_page_2xx() throws Exception {
        when(generateRandomNumberService.generate()).thenReturn(0);
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok - 2xx")));
    }

    @Test
    @DisplayName("GET / 테스트 - 3xx")
    public void get_index_page_3xx() throws Exception {
        when(generateRandomNumberService.generate()).thenReturn(1);
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(content().string(containsString("redirect - 3xx")));
    }

    @Test
    @DisplayName("GET / 테스트 - 4xx")
    public void get_index_page_4xx() throws Exception {
        when(generateRandomNumberService.generate()).thenReturn(2);
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(containsString("error - 4xx")));
    }

    @Test
    @DisplayName("GET / 테스트 - 5xx")
    public void get_index_page_5xx() throws Exception {
        when(generateRandomNumberService.generate()).thenReturn(3);
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(containsString("error - 5xx")));
    }
}