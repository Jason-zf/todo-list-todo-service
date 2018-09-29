package com.thoughtworks.training.todoservice.service;

import com.google.common.primitives.Booleans;
import com.thoughtworks.training.todoservice.exception.BadRequestException;
import com.thoughtworks.training.todoservice.exception.NotFoundException;
import com.thoughtworks.training.todoservice.model.Todo;
import com.thoughtworks.training.todoservice.model.User;
import com.thoughtworks.training.todoservice.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TagService tagService;

    public Todo getOne(Long id) throws NotFoundException {
        Todo todo = todoRepository.findOne(id);
        if (todo == null) {
            throw new NotFoundException();
        }
        return todo;
    }

    public Page<Todo> getAll(Pageable pageable, Optional<List<String>> tag, Optional<Date> startDate, Optional<Date> endDate) throws BadRequestException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Booleans.countTrue(startDate.isPresent(), endDate.isPresent()) == 1) {
            throw new BadRequestException("startDate and endDate appear in same time");
        } else {
            if (startDate.isPresent() && tag.isPresent()) {
                return todoRepository.findAllByUserIdAndTags_NameInAndDueDateIsBetween(user.getId(), tag.get(), startDate.get(), endDate.get(), pageable);
            } else if (startDate.isPresent()) {
                return todoRepository.findAllByUserIdAndDueDateIsBetween(user.getId(), startDate.get(), endDate.get(), pageable);
            } else if (tag.isPresent()) {
                return todoRepository.findAllByUserIdAndTags_nameIn(user.getId(), tag.get(), pageable);
            } else {
                return todoRepository.findByUserId(user.getId(), pageable);
            }
        }
    }

    public Todo addOne(Todo todo) {
        tagService.save(todo.getTags());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        todo.setUserId(user.getId());
        return todoRepository.save(todo);
    }

    public String deleteOne(Long id) {
        todoRepository.delete(id);
        return "delete";
    }

    public Todo updateOne(Todo todo) {
        tagService.save(todo.getTags());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        todo.setUserId(user.getId());
        return todoRepository.save(todo);
    }

    public Page<Todo> findToDosByNameContains(String name, Pageable pageable) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return todoRepository.findAllByUserIdAndNameContains(user.getId(), name, pageable);
    }
}
