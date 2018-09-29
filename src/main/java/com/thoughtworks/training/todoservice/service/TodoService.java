package com.thoughtworks.training.todoservice.service;

import com.thoughtworks.training.todoservice.model.Todo;
import com.thoughtworks.training.todoservice.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TagService tagService;

    public Todo getOne(Long id) {
        return todoRepository.findOne(id);
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Todo addOne(Todo todo) {
        tagService.add(todo.getTags());
        return todoRepository.save(todo);
    }

    public String deleteOne(Long id) {
        todoRepository.delete(id);
        return "delete";
    }

    public Todo updateOne(Todo todo) {
        tagService.add(todo.getTags());
        return todoRepository.save(todo);
    }
}
