package com.myprojecticaro.poc_scoped_values.scoped;

import java.lang.ScopedValue;

public class ScopedValueExample {

    private final BusinessService service = new BusinessService();

    public void run() {
        ScopedValue.where(ScopedContext.USER, "icaro")
                .run(() -> {
                    service.process();
                    nestedCall();
                });
    }

    private void nestedCall() {
        System.out.println("Nested user: " + ScopedContext.USER.get());
    }
}