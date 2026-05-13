package com.myprojecticaro.poc_scoped_values.propagation.util;

public class ThreadSwitchingExperiment {

    public void execute() {

        ContextLogger.info("Before thread switch");

        Thread.startVirtualThread(() -> {

            ContextLogger.info("Inside new virtual thread");

        });

        ContextLogger.info("After thread switch");
    }
}