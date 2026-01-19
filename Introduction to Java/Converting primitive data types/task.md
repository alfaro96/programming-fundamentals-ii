# Converting primitive data types

This example **demonstrates** how to **handle** different **primitive data types and** the **process** of **widening and narrowing** conversions.

---

## What does it do?

The program **`Conversion` declares variables** of **various sizes** to show how Java moves data between types:

* It **performs widening conversion** by automatically assigning a smaller type to a larger type without any special syntax.
* The code **illustrates narrowing conversion** by using an explicit cast to fit a larger value into a smaller variable.
* It **shows how** the **compiler generates an error** if you **attempt** to perform a **narrowing conversion without** a **cast**.
* The program **highlights data loss** by printing a value that has been truncated during a narrowing process.
* It **demonstrates** the use of **type suffixes** like **`L`** and **`F`** to **explicitly define literals** as **`long`** or **`float`**.

---

## Key concepts

* **Widening conversion happens automatically** when moving from a smaller type to a larger type **because** there is **no risk** of losing information.
* **Narrowing conversion requires** an **explicit cast** in parentheses **because** the operation **might result** in **data loss** or lower precision.
* **Compiler errors** act as a **safety net** to **prevent** developers from accidentally **losing precision** when assigning a **`double`** to an **`int`**.
* **Precision loss** occurs **during narrowing when** the **decimal part** of a number **is discarded** or the value exceeds the capacity of the target type.
* **Literal suffixes** are **mandatory for certain types** like **`long`** and **`float`** to tell the compiler exactly how much memory to allocate for a value.

---

## View the files

* [Conversion.java](file://Converting%20primitive%20data%20types/src/Conversion.java)