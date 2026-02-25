# Extracting Behaviors into Independent Contracts (Interfaces)

While abstract classes are great for creating a family hierarchy (like `Forward` with `Player`), they suffer from one major limitation: a class can only inherit from one parent. What happens when unrelated objects share a common behavior? This exercise introduces **interfaces**, the ultimate tool for flexible code design.

---

## What does it do?

This code implements a highly polymorphic structure using behavioral contracts:

* We define the `Interviewable` and `Sponsorable` interfaces using the **`interface` keyword**.
* We demonstrate **multiple implementation** by having the `Forward` class implement both interfaces simultaneously, while inheriting from `Player`.
* We use the `implements` keyword on a completely unrelated class (`Coach`) to force it to define the exact same behavior.
* In the `Main` class, we leverage **universal grouping** to put a `Forward` and a `Coach` into the exact same array of `Interviewable` objects.

---

## Key concepts

Mastering interfaces requires understanding their specific rules, which differ from traditional inheritance:

* **The contract**: A class implementing an interface must define **all its methods**, or be declared abstract itself.
* **Naming convention**: Interface names are typically adjectives or roles (e.g., `Interviewable`, `Sponsorable`) to describe what an object *can do*, not what it *is*.
* **No constructors**: Interfaces cannot have constructors because they do not represent instantiable objects, only behaviors.
* **Strict field rules**: Any attributes declared inside an interface are implicitly treated as `public`, `static`, and `final` (constants).
* **Interface references**: You can safely perform **upcasting** and **downcasting** using the interface as the reference type, treating it just like a superclass for extreme polymorphism.

---

## View the files

* [Interviewable.java](file://src/soccerRoles/Interviewable.java)
* [Sponsorable.java](file://src/soccerRoles/Sponsorable.java)
* [Player.java](file://src/soccerRoles/Player.java)
* [Coach.java](file://src/soccerRoles/Coach.java)
* [Forward.java](file://src/soccerRoles/Forward.java)
* [Main.java](file://src/Main.java)