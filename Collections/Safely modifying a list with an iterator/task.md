# Safely modifying a list with an iterator

This example **demonstrates** the **correct way** to **remove elements** from a **list** while iterating **using** an explicit `Iterator`.

---

## What does it do?

The program **creates** a **list** of soccer player names **and safely removes one** of them **during iteration**.

**Instead of** using a `for-each` loop, **which** would **throw** a `ConcurrentModificationException`, it **uses** an explicit `Iterator` **and** its `remove()` method, which **keeps** the **collection and iterator in sync**.

---

## Key concepts

* You **should not modify** a **list directly** inside a `for-each` loop.
* To **remove elements during iteration**, use an **explicit** `Iterator` **and** call its `remove()` method **after** `next()`.
* This **avoids** `ConcurrentModificationException` and maintains a consistent internal state.

---

## View the files

* [Main.java](file://collections/Safely%20modifying%20a%20list%20with%20an%20iterator/src/Main.java)
