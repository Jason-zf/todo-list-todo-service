package com.thoughtworks.training.todoservice.client;

import com.thoughtworks.training.todoservice.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service", url = "http://localhost:8082")
public interface UserClient {
    @GetMapping("/users/verification")
    User verify(@RequestHeader("token") String token);
}
