package com.myprojecticaro.poc_scoped_values.propagation;

import java.util.concurrent.StructuredTaskScope;

public class StructuredPropagationExample {

    public void execute() throws Exception {

        ContextLogger.info("Parent task started");

        try (var scope = StructuredTaskScope.open()) {

            scope.fork(() -> {

                ContextLogger.info("Child task A");

                return null;
            });

            scope.fork(() -> {

                ContextLogger.info("Child task B");

                return null;
            });

            scope.join();
        }

        ContextLogger.info("Parent task completed");
    }
}