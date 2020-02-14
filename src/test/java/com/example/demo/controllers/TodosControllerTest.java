package com.example.demo.controllers;

import com.example.demo.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;

public class TodosControllerTest extends IntegrationTest {
    @Autowired
    TodosController controller;

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnSuccessfulResult() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/todos/success")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is("1")));
    }

    @Test
    public void shouldReturnSuccessfulDelayedResult() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/todos/delayed")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is("1")));
    }

    @Test
    public void shouldReturn503WhenServiceIsDown() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/todos/connection-reset")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().is(503));
    }
}
