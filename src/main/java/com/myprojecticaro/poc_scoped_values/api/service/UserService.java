package com.myprojecticaro.poc_scoped_values.api.service;

import com.myprojecticaro.poc_scoped_values.api.context.ScopedContext;
import com.myprojecticaro.poc_scoped_values.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository = new UserRepository();

    public void process() {
        var userId =  repository.findUser();

        System.out.println("[Service] userId=" + userId);
    }
}