# 🧪 POC - Scoped Values in Java (Java 25)


##📌 Objective


This POC aims to deeply explore the Scoped Values feature, introduced in Java as part of the evolution of modern concurrency (especially alongside Virtual Threads).


By the end of this project, you will understand:

The concept of ScopedValue



- Why it was created

- Differences compared to ThreadLocal

- How to use it in practice

- How it behaves with Virtual Threads

## 🧠 What are Scoped Values?

ScopedValue is an API that allows sharing immutable data within a well-defined execution scope.

Unlike ThreadLocal, the value:

- Exists only within a controlled block

- Cannot be modified (immutable)

- Does not leak across executions

- Is inherently safe for concurrent use


## 🤔 Why was this feature created?

ThreadLocal has several issues in modern concurrency models:

- ❌ Risk of memory leaks

- ❌ Hard lifecycle management

- ❌ Problems with thread pools

- ❌ Poor fit for Virtual Threads

- ❌ Mutable state → prone to subtle bugs


With the introduction of Virtual Threads, the execution model changed significantly. Creating thousands (or millions) of lightweight threads makes ThreadLocal inefficient and dangerous if misused.


👉 `ScopedValue` was introduced to provide a safe, predictable, and structured way to propagate contextual data.

⚖️ ScopedValue vs ThreadLocal
Feature      ThreadLocal ScopedValue
Mutability.  Mutable       Immutable
Scope.       Entire thread Explicit block scope
Lifecycle.   Manual.    Automatic
Memory leaks Possible.  Avoided
Virtual Threads Problematic Designed for it
Safety Low High

## 🏗️ Project Structure

```
com.example.scoped
 ├── Main.java
 ├── ScopedContext.java
 ├── BusinessService.java
 ├── ThreadLocalExample.java
 ├── ScopedValueExample.java
 └── ScopedWithVirtualThreads.java
```


### 1️⃣ Scoped Context


```
package com.example.scoped;

import java.lang.ScopedValue;

public class ScopedContext {

    public static final ScopedValue<String> USER = ScopedValue.newInstance();
}

```

### 2️⃣ Business Service

```
package com.example.scoped;

public class BusinessService {

    public void process() {
        String user = ScopedContext.USER.get();
        System.out.println("Processing for user: " + user);
    }
}

```

### 3️⃣ ScopedValue Example

```
package com.example.scoped;

import java.lang.ScopedValue;

public class ScopedValueExample {

    private final BusinessService service = new BusinessService();

    public void run() {
        ScopedValue.where(ScopedContext.USER, "icaro")
                .run(() -> {
                    service.process();
                    nestedCall();
                });
    }

    private void nestedCall() {
        System.out.println("Nested user: " + ScopedContext.USER.get());
    }
}

```

### 4️⃣ ScopedValue with Virtual Threads

```
package com.example.scoped;

import java.util.concurrent.Executors;

public class ScopedWithVirtualThreads {

    public void run() throws InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {

            executor.submit(() ->
                ScopedValue.where(ScopedContext.USER, "user-1")
                    .run(() -> {
                        System.out.println("Thread 1: " + ScopedContext.USER.get());
                    })
            );

            executor.submit(() ->
                ScopedValue.where(ScopedContext.USER, "user-2")
                    .run(() -> {
                        System.out.println("Thread 2: " + ScopedContext.USER.get());
                    })
            );
        }
    }
}

```

### 5️⃣ ThreadLocal Example (Pitfall)

```

package com.example.scoped;

public class ThreadLocalExample {

    private static final ThreadLocal<String> USER = new ThreadLocal<>();

    public void run() {
        USER.set("icaro");

        process();

        // Common bug: forgot to clean up
        // USER.remove();
    }

    private void process() {
        System.out.println("Processing for user: " + USER.get());
    }
}

```

### ⚠️ Real Problem with ThreadLocal

```
executor.submit(() -> {
    USER.set("user-1");
    process();
});

executor.submit(() -> {
    process(); // may accidentally reuse user-1 😱
});

```

## 🚀 Main Class

```
package com.example.scoped;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("=== ScopedValue ===");
        new ScopedValueExample().run();

        System.out.println("\n=== Virtual Threads + ScopedValue ===");
        new ScopedWithVirtualThreads().run();

        System.out.println("\n=== ThreadLocal (problem) ===");
        new ThreadLocalExample().run();
    }
}

```

### 💡 Key Insight

ScopedValue is not a direct replacement for ThreadLocal.

Use ScopedValue when:

You need immutable contextual data

You want safe propagation across layers

You are using Virtual Threads

You want to avoid concurrency bugs Ideas)

### 🧪 Next Steps (Advanced POC Ideas)

To go deeper:

- Implement a RequestContext (e.g., correlationId, userId)

- Simulate an API flow (Controller → Service → Repository)

- Propagate context across layers

Combine with:

Virtual Threads
Structured Concurrency
Logging/Tracing (observability)

🧨 Final Thought
If you ignore immutability, you lose the main benefit of this feature.
ScopedValue is not about “storing data”.
👉 It is about modeling context in a safe, structured, and predictable way