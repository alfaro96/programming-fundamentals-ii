# Listing Object methods

Every class in Java automatically inherits from a base class called `Object`. This exercise explores the fundamental methods provided by this built-in toolbox.

---

## What does it do?

This code demonstrates how to override and utilize the default object behaviors to make your classes smarter and easier to manage:

* You will see how **`toString()`** transforms a generic memory reference into a human-readable **string representation**.
* It introduces the **`equals()`** method to check if the actual data inside two objects is **logically equivalent**.
* It uses **`getClass()`** to strictly identify the **runtime class** of an object before performing a downcast.

---

## Key concepts

When managing object lifecycles and comparisons, keep these rules in mind:

* **Logical equivalence versus identity**: Using `==` only checks if two references point to the exact same memory address. Overriding `equals()` allows you to define what makes two objects truly "equal" based on their attributes.
* **Debugging**: Always override `toString()`. It is **essential for debugging and logging**, as it allows you to instantly see the state of an object.
* **Safe downcasting**: While `instanceof` is great, `getClass()` provides a stricter, exact match of the **runtime class**, giving you another safe way to verify types.
* **Object duplication**: Even though a `clone()` method exists, it is **widely discouraged**. Using a **copy constructor** (a constructor that takes an object of the same type as a parameter) is the recommended way to create object duplicates.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Main.java](file://src/Main.java)
