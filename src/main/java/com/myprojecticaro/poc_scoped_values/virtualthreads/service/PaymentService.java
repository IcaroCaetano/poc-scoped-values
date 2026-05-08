package com.myprojecticaro.poc_scoped_values.virtualthreads.service;

import com.myprojecticaro.poc_scoped_values.virtualthreads.util.ContextLogger;

public class PaymentService {

    public void processPayment() {

        ContextLogger.info("Processing payment");

        sleep(1500);

        ContextLogger.info("Payment approved");
    }

    private void sleep(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}