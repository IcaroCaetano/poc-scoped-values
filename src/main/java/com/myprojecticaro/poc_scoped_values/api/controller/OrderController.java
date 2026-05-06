package com.myprojecticaro.poc_scoped_values.api.controller;

import com.myprojecticaro.poc_scoped_values.api.context.RequestContext;
import com.myprojecticaro.poc_scoped_values.api.context.ScopedContext;
import com.myprojecticaro.poc_scoped_values.api.service.OrderService;

import java.lang.ScopedValue;
import java.util.UUID;

public class OrderController {

    private final OrderService service = new OrderService();

    public void handleRequest(String userId) {

        RequestContext context = new RequestContext(
                userId,
                UUID.randomUUID().toString()
        );

        ScopedValue
                .where(ScopedContext.CONTEXT, context)
                .run(() -> {
                    service.processOrder();
                });
    }
}