# Closing resources with finally

This example **demonstrates** how to guarantee the execution of critical cleanup code using the `finally` block, regardless of whether an exception occurs.

---

## What does it do?

The program **simulates** a reporting system that connects to a database to save match statistics.

* It **opens** a connection using the `MatchReporter` class.
* Inside the `try` block, it **attempts** to save the data. We intentionally pass invalid data to trigger an `IllegalArgumentException`.
* The `catch` block **intercepts** the error and prints a warning.
* The `finally` block **executes immediately after**, ensuring that the database connection is securely closed. This happens even though the save operation failed!

---

## Key concepts

* **Absolute guarantee**: The `finally` block contains code that always runs, no matter the outcome of the `try` or `catch` blocks.
* **Block placement**: It is used immediately after a `try` or `catch` block.
* **Unstoppable execution**: It runs even if an exception is thrown, or if a `return` or `break` statement is used inside the `try` block.
* **Cleanup operations**: It is essential for releasing memory and safely closing files, streams, or external database connections to prevent resource leaks.

---

## View the files

* [MatchReporter.java](file://src/MatchReporter.java)
* [Main.java](file://src/Main.java)
