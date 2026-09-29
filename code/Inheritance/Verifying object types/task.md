# Verifying object types safely

When working with polymorphism, assuming an object's type during a downcast can lead to fatal runtime crashes. This exercise introduces the ultimate safety net: the `instanceof` operator.

---

## What does it do?

This code contrasts the danger of blind casting with the reliability of verified casting:

* We first explore an **unsafe downcast**. The compiler trusts you, but the `JVM` will throw a `ClassCastException` if you try to cast a `Goalkeeper` into a `Forward`.
* Next, we introduce a **safety check** using `instanceof` to gracefully prevent the program from crashing.
* Finally, we demonstrate a critical restriction: you **cannot apply this operator to primitive types** (like `int` or `double`).

---

## Key concepts

To guarantee safe downcasting, always follow these rules:

* **Purpose**: Use `instanceof` to actively check if an object belongs to a **specific class or subclass** before attempting a cast.
* **Syntax and Evaluation**: The expression `object instanceof Class` will evaluate to `true` if there is a match. It acts as a shield, returning `false` if there is a **mismatch or if the object is `null`**.
* **Limitation**: Keep in mind that this operator is strictly designed for **reference types** (objects and arrays). It **cannot be used** with standard primitive types.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Goalkeeper.java](file://src/playerRoles/Goalkeeper.java)
* [Main.java](file://src/Main.java)
