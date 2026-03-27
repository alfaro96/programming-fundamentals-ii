# Understanding Checked vs. Unchecked Exceptions

This example **demonstrates** the difference between an exception the compiler forces you to handle (checked) and one it ignores until the program runs (unchecked).

---

## What does it do?

The program **explores** two different methods in our `Forward` class:

* It **attempts** to load the player's tactical profile from a file. Since external files might be missing, the compiler treats this as a **checked exception** and strictly forces us to handle it.
* It **calculates** the player's shooting accuracy using division. Since passing a zero is a programming logic error, the compiler treats this as an **unchecked exception** and does not force us to handle it.
* The `Main` class **shows** how the compiler reacts differently to each method call.

---

## Key concepts

* **Checked exceptions**:The compiler strictly checks these. You **must** handle them with a `try` and `catch` block or declare them with `throws`, otherwise the code will not compile. They usually involve external resources (like files or networks).
* **Unchecked Exceptions**: The compiler does not check these. Handling them is **optional** during compilation. They usually represent logic bugs or programming errors (like dividing by zero or accessing a `null` object) and will crash the program at runtime if not fixed.

---

## View the files

* [Forward.java](file://src/playerRoles/Forward.java)
* [Main.java](file://src/Main.java)