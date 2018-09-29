package com.thoughtworks.training.todoservice.controller;

import com.thoughtworks.training.todoservice.model.Tag;
import com.thoughtworks.training.todoservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> get(){
        return tagService.getAll();
    }
}
