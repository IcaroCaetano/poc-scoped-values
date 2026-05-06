package com.myprojecticaro.poc_scoped_values.api.infrastructure;

import com.myprojecticaro.poc_scoped_values.api.context.RequestContext;
import com.myprojecticaro.poc_scoped_values.api.context.ScopedContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.ScopedValue;
import java.util.UUID;

@Component
public class ScopedValueFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String userId = request.getHeader("x-user-id");
        String correlationId = UUID.randomUUID().toString();

        RequestContext context = new RequestContext(userId, correlationId);

        ScopedValue.where(ScopedContext.CONTEXT, context)
                .run(() -> {
                    try {
                        filterChain.doFilter(request, response);
                    } catch (IOException | ServletException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}