# Manually throwing an exception with `throw`

This example **demonstrates** how to **manually raise** an **exception** using the `throw` keyword.

---

## What does it do?

The code **defines** a `divide` **method** that **checks if** the **divisor** is **zero**. **If so**, it **uses** the `throw` keyword **to** manually **raise** an `ArithmeticException` with a custom message. This **shows how** you can **detect invalid input and throw exceptions intentionally** to signal problems.

---

## Key concepts

* The `throw` keyword is **used to create and raise** an **exception object**.
* It must be followed by an instance of a subclass of `Throwable`, like `ArithmeticException`.
* `throw` is used **inside** a **method body**, not in the method declaration.

---

## View the file

[Main.java](file://Exceptions/Manually%20throwing%20an%20exception%20with%20throw/src/Main.java)
