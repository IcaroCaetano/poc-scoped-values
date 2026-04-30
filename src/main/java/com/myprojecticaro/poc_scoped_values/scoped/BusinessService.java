package com.myprojecticaro.poc_scoped_values.scoped;

public class BusinessService {

    public void process() {
        String user = ScopedContext.USER.get();
        System.out.println("Processing for user: " + user);
    }
}