# Bridging primitives and objects

This example **demonstrates** how **wrapper classes** act as an **upgrade** to standard **primitives**, **providing** built-in **utilities** and constants.

---

## What does it do?

The program **explores** the **capabilities** of classes like **`Integer`**, **`Double`**, and **`Character`** to show their practical advantages:

* It **performs conversions** by turning text **strings** into **numerical values** using parsing methods.
* The code **accesses constants** to **identify** the physical limits of data types, such as the **maximum and minimum values** a variable can hold.
* It **utilizes character analysis** tools to **check** if a **specific symbol** is a **letter**, a **digit**, or **whitespace without** writing **complex logic**.
* The program **illustrates autoboxing**, showing how Java **automatically converts between** a **primitive** and its **corresponding object** wrapper.
* It **serves as a guide** to **understanding when** to use a **simple value** and **when** the "superpowers" of a **wrapper object** are necessary.

---

## Key concepts

* **Wrapper classes**: Specialized classes (`Integer`, `Boolean`, `Double`, etc.) that "**wrap**" a primitive value inside** an **object**.
* **Static utilities**: Methods that belong to the class itself, **used** for common **tasks like parsing** (converting `String` to numbers).
* **Type limits**: Built-in **constants** like `MAX_VALUE` and `MIN_VALUE` that **define** the **mathematical boundaries** of a data type in memory.
* **Autoboxing and unboxing**: The **seamless transition** where Java treats **primitives** and **wrappers** as **interchangeable** in most situations.

---

## View the files

* [Main.java](file://src/Main.java)