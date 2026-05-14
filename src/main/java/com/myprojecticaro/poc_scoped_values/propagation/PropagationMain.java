package com.myprojecticaro.poc_scoped_values.propagation;

import com.myprojecticaro.poc_scoped_values.virtualthreads.context.RequestContext;
import com.myprojecticaro.poc_scoped_values.virtualthreads.context.ScopedRequestContext;

import java.lang.ScopedValue;
import java.util.UUID;

public class PropagationMain {

    public static void main(String[] args) throws Exception {

        var context = new RequestContext(
                "icaro",
                UUID.randomUUID().toString()
        );

        ScopedValue.where(
                ScopedRequestContext.CONTEXT,
                context
        ).run(() -> {

            try {

                new StructuredPropagationExample().execute();

                new ManualPropagationExample().execute();

                new ThreadSwitchingExperiment().execute();

                //new BrokenPropagationExample().execute();

            } catch (Exception e) {

                e.printStackTrace();
            }
        });
    }
}