package com.myprojecticaro.poc_scoped_values.propagation.util;

import com.myprojecticaro.poc_scoped_values.virtualthreads.context.ScopedRequestContext;

public final class ContextLogger {

    private ContextLogger() {
    }

    public static void info(String message) {

        var context = ScopedRequestContext.get();

        System.out.printf(
                "[userId=%s] [correlationId=%s] [thread=%s] %s%n",
                context.userId(),
                context.correlationId(),
                Thread.currentThread(),
                message
        );
    }
}