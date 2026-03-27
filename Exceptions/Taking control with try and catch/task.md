# Taking control with `try` and `catch`

This example **demonstrates** how to handle exceptions using a `try` and `catch` block to prevent unexpected crashes in our player hierarchy.

---

## What does it do?

The program **attempts** to calculate the forward's accuracy by dividing by zero, which would normally cause an `ArithmeticException` and crash the program.

* It **wraps** the risky method call inside a `try` block.
* **Instead of crashing**, the exception is **intercepted** by the `catch` block.
* It **resolves** the problem immediately by printing a warning and assigning a default value. The program then continues running and finishes successfully!

---

## Key concepts

* **Preventing crashes**: Handling exceptions stops the program from terminating unexpectedly.
* **Immediate resolution**: The issue is managed directly in the code where it occurs.
* **The `try` block**: Wraps the risky method call that might trigger an exception.
* **The `catch` block**: Intercepts the exception if launched and executes a safe backup plan.

---

## View the files

* [Forward.java](file://src/playerRoles/Forward.java)
* [Main.java](file://src/Main.java)