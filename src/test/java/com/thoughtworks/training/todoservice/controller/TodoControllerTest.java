package com.thoughtworks.training.todoservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.thoughtworks.training.todoservice.model.Todo;
import com.thoughtworks.training.todoservice.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    @WithMockUser
    public void shouldGetOneTodoGivenId() throws Exception {
        given(todoService.getOne(any())).willReturn(Todo.builder().name("test").status("To Do").build());

        mockMvc.perform(get("/todos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.status").value("To Do"));
    }

    @Test
    @WithMockUser
    public void shouldGetAllTodo() throws Exception {
        given(todoService.getAll()).willReturn(ImmutableList.of(Todo.builder().name("test").status("To Do").build(), Todo.builder().name("test2").status("In progress").build()));

        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[0].status").value("To Do"))
                .andExpect(jsonPath("$[1].name").value("test2"))
                .andExpect(jsonPath("$[1].status").value("In progress"));
    }

    @Test
    @WithMockUser
    public void shouldAddOne() throws Exception {

        Todo todo = Todo.builder().name("test").status("In progress").build();
        given(todoService.addOne(todo)).willReturn(todo);

        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON)
                .content(parseIntoJsonString(todo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.status").value("In progress"));
    }

    private String parseIntoJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser
    public void shouldDeleteOne() throws Exception {
        given(todoService.deleteOne(any())).willReturn("delete");

        mockMvc.perform(delete("/todos/{id}", 1))
                .andExpect(status().isOk());

        verify(todoService, times(1)).deleteOne(1L);
    }

    @Test
    @WithMockUser
    public void shouldUpdateOne() throws Exception {
        Todo todo = Todo.builder().name("test").status("To Do").build();
        given(todoService.updateOne(any())).willReturn(todo);

        mockMvc.perform(put("/todos").contentType(MediaType.APPLICATION_JSON)
                .content(parseIntoJsonString(todo)))
                .andExpect(status().isOk());

        verify(todoService, times(1)).updateOne(todo);
    }

}