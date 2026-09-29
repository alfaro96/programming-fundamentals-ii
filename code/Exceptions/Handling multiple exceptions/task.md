# Handling multiple exceptions

This example **demonstrates** how to handle different types of errors individually using multiple `catch` blocks and enforces the strict hierarchical order required by Java.

---

## What does it do?

The program **expands** the `Forward` class by adding a method to process a player transfer to a new team. This process can fail for two specific reasons: a missing team name or a negative transfer fee.

* The `Main` class **attempts** a transfer with invalid data.
* The `try` block is followed by **three different `catch` blocks**.
* It **evaluates** the blocks from top to bottom. The first two catch highly specific errors (`IllegalArgumentException` and `NullPointerException`).
* The final block catches the general `Exception` class. It acts as a safety net for any unexpected errors that weren't caught by the specific blocks above it.

---

## Key concepts

* **Multiple handlers**: A single `try` block can have multiple `catch` blocks to provide different solutions for different problems.
* **Top-to-bottom**: Java checks the `catch` blocks in order; only the first match executes.
* **Strict hierarchy**: You must order them from the most specific (child classes) to the most general (parent classes like `Exception`).
* **Compiler error**: If you place `catch (Exception e)` at the very top, the compiler will throw an error because it intercepts everything, making the specific blocks below it completely unreachable.

---

## View the files

* [Forward.java](file://src/playerRoles/Forward.java)
* [Main.java](file://src/Main.java)