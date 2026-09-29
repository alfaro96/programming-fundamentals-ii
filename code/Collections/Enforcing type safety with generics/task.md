# Enforcing type safety with generics

This example **demonstrates** how to **use** a **generic** `ArrayList` as a resizable array that **holds only objects** of a **specific type**.

---

## What does it do?

The program **defines** two soccer player roles: `Forward` and `Midfielder`.

In the `Main` class:

* A **generic** `ArrayList<Forward>` is **created** with an initial capacity of 3.
* Several `Forward` **instances** are **added**, more than the initial capacity, showing that the list resizes automatically.
* The list is iterated using a standard index-based `for` loop.
* The `score()` method is **called** on each player **without casting**, thanks to generics.
* **If** you **attempt** to **add** a `Midfielder` **to** the `ArrayList<Forward>`, the **compiler** will **reject it**, preventing type errors at runtime.
* This **demonstrates** how **generics make code cleaner**, **safer**, and **easier to read** while keeping the flexibility of dynamic arrays.

---

## Key concepts

* `ArrayList<T>` is a resizable array that **only accepts elements** of **type** `T`.
* **Generics provide type safety** at compile time.
* A **generic list removes** the **need for** `instanceof` checks or casting.
* The list can grow automatically when new elements are added.

---

## View the files

* [Main.java](file://src/Main.java)
* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Midfielder.java](file://src/playerRoles/Midfielder.java)
