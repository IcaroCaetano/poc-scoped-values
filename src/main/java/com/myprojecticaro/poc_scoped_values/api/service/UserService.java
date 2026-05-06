package com.myprojecticaro.poc_scoped_values.api.service;

import com.myprojecticaro.poc_scoped_values.api.context.ScopedContext;
import com.myprojecticaro.poc_scoped_values.api.repository.UserRepository;

public class UserService {

    private final UserRepository repository = new UserRepository();

    public void processUser() {
        var ctx = ScopedContext.get();

        System.out.println("[Service] userId=" + ctx.userId());

        repository.findUser();
    }
}