package com.myprojecticaro.poc_scoped_values.propagation;


import com.myprojecticaro.poc_scoped_values.virtualthreads.context.RequestContext;
import com.myprojecticaro.poc_scoped_values.virtualthreads.context.ScopedRequestContext;

import java.lang.ScopedValue;
import java.util.concurrent.CompletableFuture;

public class ManualPropagationExample {

    public void execute() {

        RequestContext context =
                ScopedRequestContext.get();

        CompletableFuture.runAsync(() -> {

            ScopedValue.where(
                    ScopedRequestContext.CONTEXT,
                    context
            ).run(() -> {

                ContextLogger.info("Async propagated task");

            });

        }).join();
    }
}