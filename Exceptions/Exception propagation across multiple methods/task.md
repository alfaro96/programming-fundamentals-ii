# Exception propagation across multiple methods

This example **demonstrates how exceptions can be passed up** the call stack using `throws`.

---

## What does it do?

**The code **defines three methods** that **call each other**: `methodC → methodB → methodA`. `methodC` **throws** an `IOException`, and it is **not handled** in any of the methods **until** it reaches the `main` method. This is **known as exception propagation**, that is, **passing** the **exception up until** a** method handles** it **with** `try` and `catch` block.

---

## Key concepts

* **If** a **method does not handle** an **exception**, it **must declare** it using `throws`.
* **Exceptions** can **propagate up** the **call chain until** they're **caught**.
* The `main` method is the **only one with** a `try` and `catch` block in this example.
* This **lets deeper methods focus on logic and defer error handling**.

---

## View the file

[Main.java](file://Exceptions/Exception%20propagation%20across%20multiple%20methods/src/Main.java)
