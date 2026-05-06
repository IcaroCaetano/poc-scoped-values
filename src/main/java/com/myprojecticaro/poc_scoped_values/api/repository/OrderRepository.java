package com.myprojecticaro.poc_scoped_values.api.repository;

import com.myprojecticaro.poc_scoped_values.api.context.ScopedContext;

public class OrderRepository {

    public void findOrder() {
        var ctx = ScopedContext.get();

        System.out.println("[Repository] correlationId=" + ctx.correlationId());
    }
}