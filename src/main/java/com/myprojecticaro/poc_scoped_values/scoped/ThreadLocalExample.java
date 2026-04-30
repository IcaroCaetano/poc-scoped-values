package com.myprojecticaro.poc_scoped_values.scoped;

public class ThreadLocalExample {

    private static final ThreadLocal<String> USER = new ThreadLocal<>();

    public void run() {
        USER.set("icaro");

        process();

         USER.remove();
    }

    private void process() {
        System.out.println("Processing for user: " + USER.get());
    }
}