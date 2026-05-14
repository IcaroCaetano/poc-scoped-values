package com.myprojecticaro.poc_scoped_values.propagation;

public class ThreadSwitchingExperiment {

    public void execute() {

        ContextLogger.info("ThreadSwitchingExperiment: Before thread switch");

        Thread.startVirtualThread(() -> {

            ContextLogger.info("Inside new virtual thread");

        });

        ContextLogger.info("ThreadSwitchingExperiment: After thread switch");
    }
}