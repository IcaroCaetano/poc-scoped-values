package com.myprojecticaro.poc_scoped_values.virtualthreads.context;

public record RequestContext(
        String userId,
        String correlationId
) {}