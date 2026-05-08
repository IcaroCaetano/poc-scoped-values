package com.myprojecticaro.poc_scoped_values.virtualthreads.service;

import com.myprojecticaro.poc_scoped_values.virtualthreads.util.ContextLogger;

public class NotificationService {

    public void notifyUser() {

        ContextLogger.info("Sending notification");

        sleep(500);

        ContextLogger.info("Notification delivered");
    }

    private void sleep(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}