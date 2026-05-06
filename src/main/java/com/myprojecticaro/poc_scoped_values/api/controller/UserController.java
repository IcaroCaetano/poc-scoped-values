package com.myprojecticaro.poc_scoped_values.api.controller;

import com.myprojecticaro.poc_scoped_values.api.context.RequestContext;
import com.myprojecticaro.poc_scoped_values.api.context.ScopedContext;
import com.myprojecticaro.poc_scoped_values.api.service.UserService;

import java.lang.ScopedValue;
import java.util.UUID;

public class UserController {

    private final UserService service = new UserService();

    public void handleRequest(String userId) {

        RequestContext context = new RequestContext(
                userId,
                UUID.randomUUID().toString()
        );

        ScopedValue
                .where(ScopedContext.CONTEXT, context)
                .run(() -> {
                    service.processUser();
                });
    }
}