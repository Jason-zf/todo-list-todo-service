package com.thoughtworks.training.todoservice.controller;

import com.thoughtworks.training.todoservice.exception.BadRequestException;
import com.thoughtworks.training.todoservice.exception.NotFoundException;
import com.thoughtworks.training.todoservice.model.Todo;
import com.thoughtworks.training.todoservice.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Page<Todo> get(Pageable pageable,
                                      @RequestParam(value = "tag", required = false) List<String> tag,
                                      @RequestParam(value = "from", required = false) Date from,
                                      @RequestParam(value = "to", required = false) Date to) throws BadRequestException {
        return todoService.getAll(pageable, Optional.ofNullable(tag), Optional.ofNullable(from), Optional.ofNullable(to));
    }

    @GetMapping(value = "/search/{name}")
    public Page<Todo> get(Pageable pageable, @PathVariable String name) {
        return todoService.findToDosByNameContains(name, pageable);
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
