# Printing Player Details with Wildcards

This example **demonstrates** how to **use** an **upper-bounded wildcard** in a method parameter **to support multiple subclasses of** a **superclass**.

---

## What does it do?

The program **defines** a **method** to **print player details** that **accepts** a **list** of **any type** that extends `Player`, **using** a **wildcard**: `? extends Player`.

In the `Main` class:

* A **list** of `Forward` **players and** a **list** of `Midfielder` **players** are **created** and populated.
* **Both** lists are **passed** to a **single method** that **works** for **any subclass** of `Player`.

This shows how wildcards allow writing flexible, type-safe methods that work with multiple related types.

---

## Key concepts

* **Generics** are **invariant**, a **list** of a **subclass can't be passed** as a **list** of its **superclass**.
* **Wildcards allow accepting** a **list** of **any subclass**.
* This is **useful** for **read-only access** to lists of related types.

---

## View the files

* [Main.java](file://collections/Printing%20player%20details%20with%20wildcards/src/Main.java)
* [Player.java](file://collections/Printing%20player%20details%20with%20wildcards/src/playerRoles/Player.java)
* [Forward.java](file://collections/Printing%20player%20details%20with%20wildcards/src/playerRoles/Forward.java)
* [Midfielder.java](file://collections/Printing%20player%20details%20with%20wildcards/src/playerRoles/Midfielder.java)
