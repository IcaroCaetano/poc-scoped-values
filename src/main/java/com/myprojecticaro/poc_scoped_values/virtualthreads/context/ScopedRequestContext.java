package com.myprojecticaro.poc_scoped_values.virtualthreads.context;

import java.lang.ScopedValue;

public final class ScopedRequestContext {

    private ScopedRequestContext() {}

    public static final ScopedValue<RequestContext> CONTEXT = ScopedValue.newInstance();

    public static RequestContext get() {

        return CONTEXT.get();
    }
}