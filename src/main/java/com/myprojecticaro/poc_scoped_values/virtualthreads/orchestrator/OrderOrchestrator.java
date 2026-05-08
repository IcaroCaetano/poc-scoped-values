package com.myprojecticaro.poc_scoped_values.virtualthreads.orchestrator;

import com.myprojecticaro.poc_scoped_values.virtualthreads.service.FraudAnalysisService;
import com.myprojecticaro.poc_scoped_values.virtualthreads.service.NotificationService;
import com.myprojecticaro.poc_scoped_values.virtualthreads.service.PaymentService;
import com.myprojecticaro.poc_scoped_values.virtualthreads.util.ContextLogger;

import java.util.concurrent.StructuredTaskScope;

public class OrderOrchestrator {

    private final FraudAnalysisService fraudService =
            new FraudAnalysisService();

    private final PaymentService paymentService =
            new PaymentService();

    private final NotificationService notificationService =
            new NotificationService();

    public void processOrder() throws Exception {

        ContextLogger.info("Starting order processing");

        try (var scope = StructuredTaskScope.open()) {

            var fraudTask = scope.fork(() -> {

                ContextLogger.info("Executing fraud analysis task");

                fraudService.analyze();

                return "FRAUD_ANALYSIS_OK";
            });

            var paymentTask = scope.fork(() -> {

                ContextLogger.info("Executing payment task");

                paymentService.processPayment();

                return "PAYMENT_OK";
            });

            scope.join();

            fraudTask.get();
            paymentTask.get();
        }

        notificationService.notifyUser();

        ContextLogger.info("Order processing completed");
    }
}