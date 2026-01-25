# Enforcing architectural clarity and resolving scope ambiguity

This example **demonstrates** how to **use** the **`this`** keyword to **distinguish** between **instance fields** and **local parameters**, ensuring the compiler always knows exactly which data you are referencing.

---

## What does it do?

The program **refines** the **`Player`** class by explicitly **using** the **self-reference pointer** to manage internal state and behavior:

* It **resolves shadowing** in the constructor, where parameter names are identical to field names, by **using `this`** to target the object's attributes.
* The code **enforces clarity** in standard methods by **prefixing attribute** access **with `this`**, making the architectural intent obvious to any developer reading the code.
* It **demonstrates internal delegation** by **using `this`** to **call one method from** within **another**, showing how an object can trigger its own behaviors.

---

## Key concepts

* **Scope ambiguity**: Occurs when a **local variable** has the **same name** as a **class field**, "hiding" the field from the compiler.
* **The `this` keyword**: A **reference variable** that **points** to the **current object instance**. It is the "me" or "myself" of the object world.
* **Field access**: Using **`this.attributeName` ensures** you are **modifying** the **object's permanent state**, not a temporary local variable.
* **Method invocation**: Using **`this.methodName()`** explicitly **shows** that the **behavior** being **triggered** belongs to the **same instance**.

---

## View the files

* [Player.java](file://src/Player.java)
