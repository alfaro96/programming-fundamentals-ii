# Mastering object initialization

This example **demonstrates** how to **implement constructors** to simplify object creation and ensure that every instance starts with a valid state from the moment of instantiation.

---

## What does it do?

The program **evolves** the **`Player`** class by **replacing step-by-step field assignment** with a **specialized initialization** block:

* The **`Player`** class **defines** a **constructor** that requires essential data, linking the incoming parameters directly to the object's fields.
* The **`Main`** program **instantiates** the **object** in a **single operation**, passing values directly to the `new` operator.
* It **illustrates** the **transition** from a "**blank**" **reference** to a **fully initialized object**.
* The structure **shows** how the object's **methods** have **immediate access** to the **data provided during construction**.

---

## Key concepts

* **Constructor**: A **special block** of **code** that shares the **name** of the **class** and is **executed during** the **`new`** operation.
* **State initialization**: The process of **assigning initial values** to **attributes** to **ensure** the **object** is **functional** from its creation.
* **Default constructor removal**: Java **automatically retires** the **no-argument constructor once** a **custom** one is **explicitly declared**.

---

## View the files

* [Player.java](file://Mastering%20object%20initialization/src/Player.java)
* [Main.java](file://Mastering%20object%20initialization/src/Main.java)