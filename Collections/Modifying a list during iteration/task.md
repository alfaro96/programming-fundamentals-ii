# Modifying a list during iteration

This example **demonstrates what happens when** you try to **remove elements** from a **list** while **using** a `for-each` loop, a common mistake that leads to a runtime error.

---

## What does it do?

The program **creates** a **list** of **strings** and **attempts** to **remove** an **element during** a `for-each` loop.

This **causes** a `ConcurrentModificationException`, which occurs when the **collection** is **modified directly** while being iterated **with** an **internal iterator** (like in `for-each`).

---

## Key concepts

* You **cannot modify** a **list** directly **during** a `for-each` loop.
* Doing so **throws** a `ConcurrentModificationException` because the **iterator** becomes **invalid**.
* This example sets up the **need for** a **safer approach using** an explicit `Iterator` **and** its `remove()` method.

---

## View the files

* [Main.java](file://collections/Modifying%20a%20list%20during%20iteration/src/Main.java)
