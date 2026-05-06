package com.myprojecticaro.poc_scoped_values.api.repository;

import com.myprojecticaro.poc_scoped_values.api.context.ScopedContext;

public class UserRepository {

    public void findUser() {
        var ctx = ScopedContext.get();

        System.out.println("[Repository] correlationId=" + ctx.correlationId());
    }
}