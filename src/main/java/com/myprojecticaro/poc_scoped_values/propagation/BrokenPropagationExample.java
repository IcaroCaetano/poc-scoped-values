package com.myprojecticaro.poc_scoped_values.propagation;

import java.util.concurrent.CompletableFuture;

public class BrokenPropagationExample {

    public void execute() {

        ContextLogger.info("BrokenPropagationExample - Parent task");

        CompletableFuture.runAsync(() -> {

            // provavelmente falhará
            ContextLogger.info("Async task");

        }).join();
    }
}