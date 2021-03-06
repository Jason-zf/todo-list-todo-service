package com.thoughtworks.training.todoservice.service;

import com.thoughtworks.training.todoservice.model.Tag;
import com.thoughtworks.training.todoservice.model.User;
import com.thoughtworks.training.todoservice.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public void save(List<Tag> tags) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        for (Tag tag : tags) {
            Tag newTag = tagRepository.findByName(tag.getName());
            if (newTag != null) {
                tag.setId(newTag.getId());
                tag.setUserId(userId);
            } else {
                tag.setId(tagRepository.save(tag).getId());
                tag.setUserId(userId);
            }
        }
    }

    public List<Tag> getAll() {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return tagRepository.findByUserId(userId);
    }
}
