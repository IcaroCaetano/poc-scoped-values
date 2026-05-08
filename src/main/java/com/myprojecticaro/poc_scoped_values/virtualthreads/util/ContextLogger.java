package com.myprojecticaro.poc_scoped_values.virtualthreads.util;

import com.myprojecticaro.poc_scoped_values.virtualthreads.context.ScopedRequestContext;

public final class ContextLogger {

    private ContextLogger() {}

    public static void info(String message) {

        var ctx = ScopedRequestContext.get();

        System.out.printf(
                "[userId=%s] [correlationId=%s] [thread=%s] %s%n",
                ctx.userId(),
                ctx.correlationId(),
                Thread.currentThread(),
                message
        );
    }
}