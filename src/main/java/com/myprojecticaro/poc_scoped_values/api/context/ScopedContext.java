package com.myprojecticaro.poc_scoped_values.api.context;

public final class ScopedContext {

    private ScopedContext() {}

    public static final ScopedValue<RequestContext> CONTEXT = ScopedValue.newInstance();

    public static RequestContext get() {
        return CONTEXT.get();
    }
}