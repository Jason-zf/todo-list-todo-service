package com.thoughtworks.training.todoservice.controller;

import com.google.common.collect.ImmutableList;
import com.thoughtworks.training.todoservice.model.Tag;
import com.thoughtworks.training.todoservice.model.Todo;
import com.thoughtworks.training.todoservice.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TagService tagService;

    @Test
    @WithMockUser
    public void shouldGetAllTagsGivenToken() throws Exception {
        given(tagService.getAll()).willReturn(ImmutableList.of(Tag.builder().name("test").build(), Tag.builder().name("test2").build()));

        mockMvc.perform(get("/tags"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("test"))
                .andExpect(jsonPath("$[1].name").value("test2"));

    }
}