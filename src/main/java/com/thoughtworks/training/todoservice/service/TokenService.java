package com.thoughtworks.training.todoservice.service;

import com.thoughtworks.training.todoservice.client.UserClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    private UserClient userClient;

    public Long parseToken(String token) throws AccessDeniedException {
        try {
            return userClient.verify(token).getId();
        } catch (FeignException e) {
            throw new AccessDeniedException("access denied");
        }

    }
}
