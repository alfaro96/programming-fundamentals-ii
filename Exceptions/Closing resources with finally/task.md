# Closing resources with `finally`

This example **demonstrates** how to use a `finally` block to **guarantee cleanup** after **code** that **might throw** an **exception**.

---

## What does it do?

The code **tries** to **open a file** using `FileReader`. **If** the file is **not found**, it **catches** the `FileNotFoundException`. **Regardless** of **whether** an **exception** is **thrown**, the `finally` block **always runs**, and **attempts** to **close** the **file if** it was **opened**.

---

## Key concepts

* The `finally` block **always runs**, whether an exception occurs or not.
* It's **commonly used** for **cleanup operations**, like closing files or database connections.
* In this example, the **file stream** is **closed** in the `finally` block **to ensure** it **doesn't remain open**.
* This helps **avoid resource leaks** and ensures good programming practices.

---

## View the file

[Main.java](file://Exceptions/Closing%20resources%20with%20finally/src/Main.java)
