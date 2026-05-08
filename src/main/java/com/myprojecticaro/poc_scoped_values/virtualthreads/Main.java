package com.myprojecticaro.poc_scoped_values.virtualthreads;

import com.myprojecticaro.poc_scoped_values.virtualthreads.context.RequestContext;
import com.myprojecticaro.poc_scoped_values.virtualthreads.context.ScopedRequestContext;
import com.myprojecticaro.poc_scoped_values.virtualthreads.orchestrator.OrderOrchestrator;

import java.lang.ScopedValue;
import java.util.UUID;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {

        var orchestrator = new OrderOrchestrator();

        try (var executor =
                     Executors.newVirtualThreadPerTaskExecutor()) {

            for (int i = 1; i <= 5; i++) {

                int requestId = i;

                executor.submit(() -> {

                    var context = new RequestContext(
                            "user-" + requestId,
                            UUID.randomUUID().toString()
                    );

                    ScopedValue.where(
                            ScopedRequestContext.CONTEXT,
                            context
                    ).run(() -> {

                        try {

                            orchestrator.processOrder();

                        } catch (Exception e) {

                            throw new RuntimeException(e);
                        }
                    });
                });
            }
        }
    }
}