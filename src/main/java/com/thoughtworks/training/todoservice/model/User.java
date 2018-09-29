package com.thoughtworks.training.todoservice.model;

import lombok.Builder;

@Builder
public class User {
    private Long id;
    private String name;
    private String password;
}
