# Centralizing class-level resources

This example **showcases** the power of the **`static`** keyword, a tool designed to **manage data** that belongs to the **entire class** rather than to a specific object.

---

## What does it do?

The program **transforms** the **`Player`** class into a **self-tracking registry** that keeps **count** of **every player** "**signed**" into the system:

* It **introduces** a shared variable, **`playerCount`**, which **functions** as a **global memory**; as we instantiate new players, this counter increments across the board.
* The code **presents** a static method, **`displayGlobalCount()`**, which acts as a **class-level reporter** that doesn't need a specific player to function.
* It **highlights** the **interaction** between **different scopes** by using an instance method, **`displayRegistryInfo()`**, to **merge** individual **player data** with the **global count**.
* By using **`this.`** and **`Player.`** explicitly, the code **clarifies** the **distinction** between an **object's unique state** and the **shared state** of the **class**.

---

## Key concepts

* **Shared memory**: A **`static` variable** exists in a **single location** for the **whole class**. If one object changes it, the change is visible to all.
* **Class versus instance**: **Methods** marked as **`static`** are **called using** the **class name**. They cannot use `this` because they aren't tied to a specific instance.
* **Scope hierarchy**: **Instance methods can see** both their **own** unique **attributes** and the **shared static** ones.
* **Explicit references**: Using the **class name** for **static members** and **`this`** for **instance members removes ambiguity** in complex logic.

---

## View the files

* [Player.java](file://Centralizing%20class-level%20resources/src/Player.java)
* [Main.java](file://Centralizing%20class-level%20resources/src/Main.java)