package com.myprojecticaro.poc_scoped_values.api.service;

import com.myprojecticaro.poc_scoped_values.api.context.ScopedContext;
import com.myprojecticaro.poc_scoped_values.api.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository = new OrderRepository();

    public void processOrder() {
        var correlationId = repository.findOrder();

        System.out.println("[Service] correlationId=" + correlationId);
    }
}