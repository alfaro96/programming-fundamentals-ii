# Evolving control flow

This example **demonstrates how Java improves code safety and readability** through strict boolean conditions, enhanced loops, and string-based selection.

---

## What does it do?

The program **`Evolving` showcases** the transition from legacy programming styles to more **modern and expressive structures**:

* It **enforces strict boolean conditions** in an `if` statement **to show** that **Java does not allow** using **integers as truth values**.
* The code **implements** an **enhanced for loop** (**`for-each`**) **to traverse** an **array** of strings **without managing manual indices or counters**.
* It **uses** a **switch statement to execute specific logic based on** text **input**, providing a cleaner alternative to multiple `if-else` blocks.
* The program **highlights how these features reduce common errors** like off-by-one mistakes or type mismatches.
* It **demonstrates** the **evolution of the language** towards a syntax that is both more powerful and easier for developers to maintain.

---

## Key concepts

* **Strict boolean evaluation requires** that **conditions in `if`, `while`, or `for`** must **evaluate strictly to `true` or `false`**, preventing bugs common in languages where `0` or `1` are used as booleans.
* The **enhanced for loop** (**`for-each`**) **provides** a **streamlined way to iterate** through **collections and arrays** when the index position is not needed.
* **Switch with strings allows** the program to **branch logic directly based on text** values, which was introduced to make categorical logic more intuitive.
* **Code safety** is **increased because** the **compiler catches invalid type comparisons** at compile time rather than during execution.
* **Readability and maintenance** are **improved by reducing boilerplate code** and making the developer's intent much clearer.

---

## View the files

* [Evolving.java](file://Evolving%20control%20flow/src/Evolving.java)