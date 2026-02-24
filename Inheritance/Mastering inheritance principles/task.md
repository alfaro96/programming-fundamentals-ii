# Mastering inheritance principles

This example **explores** the strict rules that govern how classes interact within a hierarchy, moving beyond basic parent-child relationships to understand the deeper principles of object-oriented design.

---

## What does it do?

The program **amplifies** our existing `Player` structure by adding a new layer to the family tree:

* It **integrates** the **`Striker`** class, which acts as a subclass of `Forward` and subsubclass of `Player`.
* The code **presents** how a `Striker` object seamlessly accesses data from all levels above it, proving the concept of **full lineage**.
* It **introduces** the **`final`** keyword to lock the `Striker` class.

---

## Key concepts

Understanding the rules of class hierarchies involves several core principles:

* **Single inheritance**: In Java, a class can only inherit from **one superclass** at a time.
* **Class hierarchy**: This mechanism creates a structured tree of related classes that can be **further extended** to add more specific behaviors.
* **Full lineage**: Subclasses do not just inherit from their immediate parent; they inherit fields and methods from **all their superclasses** all the way up the chain.
* **Final classes**: Classes marked as **`final`** represent the end of the line and **cannot be extended** by any other class.
* **Independence**: While classes in a hierarchy can interact, they **do not depend** on each other for their own basic functionality.

---

## View the files

* [Striker.java](file://src/playerRoles/Striker.java)
* [Main.java](file://src/Main.java)