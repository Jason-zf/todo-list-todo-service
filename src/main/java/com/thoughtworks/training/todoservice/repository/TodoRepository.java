package com.thoughtworks.training.todoservice.repository;

import com.thoughtworks.training.todoservice.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

}
