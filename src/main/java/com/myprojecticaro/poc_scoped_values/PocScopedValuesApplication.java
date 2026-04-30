package com.myprojecticaro.poc_scoped_values;


import com.myprojecticaro.poc_scoped_values.scoped.ScopedValueExample;
import com.myprojecticaro.poc_scoped_values.scoped.ScopedWithVirtualThreads;
import com.myprojecticaro.poc_scoped_values.scoped.ThreadLocalExample;

public class PocScopedValuesApplication {

	public static void main(String[] args) throws Exception {

		System.out.println("=== ScopedValue ===");
		new ScopedValueExample().run();

		System.out.println("\n=== Virtual Threads + ScopedValue ===");
		new ScopedWithVirtualThreads().run();

		System.out.println("\n=== ThreadLocal (problema) ===");
		new ThreadLocalExample().run();
	}

}
