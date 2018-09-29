package com.thoughtworks.training.todoservice.service;

import com.thoughtworks.training.todoservice.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    private UserClient userClient;

    public Long parseToken(String token) {
//        return userClient.verify(token).getId();
        return 1L;
    }
}
