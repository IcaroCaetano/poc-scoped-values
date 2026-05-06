package com.myprojecticaro.poc_scoped_values.scoped;

import java.util.concurrent.Executors;

public class ScopedWithVirtualThreads {

    public void run() throws InterruptedException {
        System.out.println("ScopedWithVirtualThreads Start");

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            executor.submit(() ->
                ScopedValue.where(ScopedContext.USER, "user-1")
                    .run(() -> {
                        System.out.println("Thread 1: " + ScopedContext.USER.get());
                    })
            );

            executor.submit(() ->
                ScopedValue.where(ScopedContext.USER, "user-2")
                    .run(() -> {
                        System.out.println("Thread 2: " + ScopedContext.USER.get());
                    })
            );
        }

        System.out.println("ScopedWithVirtualThreads End");
    }
}