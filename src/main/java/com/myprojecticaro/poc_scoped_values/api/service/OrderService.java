package com.myprojecticaro.poc_scoped_values.api.service;

import com.myprojecticaro.poc_scoped_values.api.context.ScopedContext;
import com.myprojecticaro.poc_scoped_values.api.repository.OrderRepository;

public class OrderService {

    private final OrderRepository repository = new OrderRepository();

    public void processOrder() {
        var ctx = ScopedContext.get();

        System.out.println("[Service] correlationId=" + ctx.correlationId());

        repository.findOrder();
    }
}