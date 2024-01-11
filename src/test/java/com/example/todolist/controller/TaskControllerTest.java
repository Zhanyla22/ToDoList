package com.example.todolist.controller;

import com.example.todolist.dto.request.AddTaskRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@FieldDefaults(level = AccessLevel.PRIVATE)
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    final long id = 1L;

    @Test
    @Order(1)
    void add() throws Exception {
        AddTaskRequest request = AddTaskRequest.builder()
                .description("Test")
                .build();

        String json = objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/task/add").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(2)
    void getById() throws Exception {
        mockMvc.perform(get("/task/get/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(3)
    void update() throws Exception {
        String requestBody = "{"
                + "\"description\": \"testtest\","
                + "\"task_status\": \"DONE\" "
                + "}";
        mockMvc.perform(put("/task/update/" + id).contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(4)
    void delete() throws Exception {
        mockMvc.perform(put("/task/delete/" + id).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @Order(5)
    void getAll() throws Exception {
        mockMvc.perform(get("/task/get-all").contentType(MediaType.APPLICATION_JSON)
                        .param("status", "ACTIVE")
                        .param("taskStatus", "DONE"))
                .andExpect(status().is2xxSuccessful());
    }
}