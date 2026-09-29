# Understanding static polymorphism

Polymorphism isn't just about inheritance. In this exercise, we explore **static polymorphism**, which allows us to define multiple behaviors for the same action name within a single class.

---

## What does it do?

This code focuses on the concept of **method overloading**:

* We have created several versions of the `train` method, all sharing the **same name but different signatures** (different parameters).
* You will see how the **compiler determines exactly which method to execute** based on the arguments you provide in the code.

---

## Key concepts

Mastering static polymorphism involves understanding how Java handles method calls before the program even starts:

* **Method overloading**: Defining multiple methods with the **same name but different signatures** within a class.
* **Compile-time decision**: The resolution happens during compilation. The compiler checks the **provided arguments** to link the call to the correct implementation.
* **Static binding**: Establishing the method link before running ensures **fast execution** while providing **flexibility** for the programmer.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Main.java](file://src/Main.java)