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