package com.myprojecticaro.poc_scoped_values.propagation;

import java.util.concurrent.CompletableFuture;

public class BrokenPropagationExample {

    public void execute() {

        ContextLogger.info("BrokenPropagationExample - Parent task started");

        CompletableFuture.runAsync(() -> {

            // provavelmente falhará
            ContextLogger.info("Async Broken task");

        }).join();
    }
}