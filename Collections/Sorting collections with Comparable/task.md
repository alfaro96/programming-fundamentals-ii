# Sorting collections with `Comparable`

This example **demonstrates** how to **implement** the **Comparable interface** to **sort** a **list** of custom objects.

---

## What does it do?

The program **creates** a **list** of **players and uses** `Collections.sort()` to:

* **Define** a natural order **based on performance scores**.
* **Organize** the list **automatically** using that order.
* **Display** the team **before and after** sorting.

**Unlike** manual sorting algorithms, the `Comparable` interface **provides** a built-in mechanism to establish **natural ordering** directly inside your custom classes.

---

## Key concepts

* The `Comparable<T>` interface is used to **define** the default **sorting logic** for a class.
* The `compareTo()` method must be overridden to establish whether an object is **greater, equal, or lesser** than another.
* Using `Collections.sort(list)` allows **sorting** to happen **automatically** without writing complex loops.
* These tools provide **efficient organization** and are **essential** for **managing data structures**.

---

## View the files

* [Player.java](file://src/Player.java)
* [Main.java](file://src/Main.java)