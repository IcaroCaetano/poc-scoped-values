package com.myprojecticaro.poc_scoped_values.virtualthreads.service;

import com.myprojecticaro.poc_scoped_values.virtualthreads.util.ContextLogger;

public class FraudAnalysisService {

    public void analyze() {

        ContextLogger.info("Starting fraud analysis");

        sleep(1000);

        ContextLogger.info("Fraud analysis completed");
    }

    private void sleep(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}