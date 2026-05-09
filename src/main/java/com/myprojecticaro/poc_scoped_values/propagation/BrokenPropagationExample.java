package com.myprojecticaro.poc_scoped_values.propagation;

import com.example.virtualthreads.util.ContextLogger;

import java.util.concurrent.CompletableFuture;

public class BrokenPropagationExample {

    public void execute() {

        ContextLogger.info("Parent task");

        CompletableFuture.runAsync(() -> {

            // provavelmente falhará
            ContextLogger.info("Async task");

        }).join();
    }
}