package com.myprojecticaro.poc_scoped_values.api.context;

public record RequestContext(
        String userId,
        String correlationId
) {}