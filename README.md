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