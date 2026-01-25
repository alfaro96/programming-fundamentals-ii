# Overloading class behavior

This example **explores** the flexibility of the **overloading** technique, which allows a class to define **multiple versions** of **constructors** and **methods** with the **same name** but **different parameter** lists.

---

## What does it do?

The program **amplifies** the versatility of the **`Player`** class by providing various ways to initialize and interact with the objects:

* It **integrates** an **optional `id`** field, **allowing** a player to be **created** either **with** a known identifier or **without** one.
* The code **presents two** distinct **constructors**; this is known as **constructor overloading**, where the compiler decides which one to run based on the arguments provided during the `new` operation.
* It **introduces** a **new version** of the **`train`** behavior through **method overloading**, enabling a player to perform a timed session or a quick exercise without specifying minutes.

---

## Key concepts

* **Method signature**: The unique **combination** of a **method's name** and its **parameter list**. Java **identifies** which **method to call** by matching this signature.
* **Constructor overloading**: Providing **multiple blueprints** for **object creation** to handle different data availability scenarios.
* **Signature matching**: For **overloading to work**, **signatures** must **differ** in the **number**, **type**, or **order** of their **parameters**.
* **Flexibility**: **Overloading makes** a **class more user-friendly** by offering different ways to achieve similar goals without forcing the user to provide unnecessary data.

---

## View the files

* [Player.java](file://Overloading%20class%20behavior/src/Player.java)
* [Main.java](file://Overloading%20class%20behavior/src/Main.java)