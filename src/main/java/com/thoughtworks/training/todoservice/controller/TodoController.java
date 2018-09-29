package com.thoughtworks.training.todoservice.controller;

import com.thoughtworks.training.todoservice.exception.NotFoundException;
import com.thoughtworks.training.todoservice.model.Todo;
import com.thoughtworks.training.todoservice.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/{id}")
    public Todo get(@PathVariable Long id) throws NotFoundException {
        return todoService.getOne(id);
    }

    @GetMapping
    public List<Todo> get() {
        return todoService.getAll();
    }

    @PostMapping
    public Todo add(@RequestBody Todo todo) {
        return todoService.addOne(todo);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id) {
        return todoService.deleteOne(id);
    }

    @PutMapping
    public Todo update(@RequestBody Todo todo) {
        return todoService.updateOne(todo);
    }
}
