package com.thoughtworks.training.todoservice.service;

import com.thoughtworks.training.todoservice.model.Tag;
import com.thoughtworks.training.todoservice.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public void add(List<Tag> tags) {
        for (Tag tag : tags) {
            Tag newTag = tagRepository.findByName(tag.getName());
            if (newTag != null) {
                tag.setId(newTag.getId());
            } else {
                tag.setId(tagRepository.save(tag).getId());
            }
        }
    }

    public List<Tag> getAll() {
        return tagRepository.findAll();
    }
}
