package com.thoughtworks.training.todoservice.repository;

import com.thoughtworks.training.todoservice.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);

    List<Tag> findByUserId(Long userId);
}
