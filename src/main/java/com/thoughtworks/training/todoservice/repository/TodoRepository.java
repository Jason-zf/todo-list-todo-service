package com.thoughtworks.training.todoservice.repository;

import com.thoughtworks.training.todoservice.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Page<Todo> findAllByUserIdAndNameContains(Long userId, String name, Pageable pageable);

    Page<Todo> findAllByUserIdAndTags_NameInAndDueDateIsBetween(Long userId, List<String> tags, Date from, Date to, Pageable pageable);

    Page<Todo> findAllByUserIdAndDueDateIsBetween(Long userId, Date from, Date to, Pageable pageable);

    Page<Todo> findAllByUserIdAndTags_nameIn(Long userId, List<String> tags, Pageable pageable);

    Page<Todo> findByUserId(Long userId, Pageable pageable);
}
