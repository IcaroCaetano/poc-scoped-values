package com.myprojecticaro.poc_scoped_values.api.controller;

import com.myprojecticaro.poc_scoped_values.api.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService service = new OrderService();

    @GetMapping("/orders")
    public String getOrder() {
        service.processOrder();
        return "ok";
    }
}