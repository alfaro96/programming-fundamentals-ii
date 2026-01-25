# Optimizing text manipulation

This example **demonstrates** the functional **differences** between the standard **`String`** class and **`StringBuilder`**, highlighting when to use each for maximum efficiency.

---

## What does it do?

The program compares how Java handles text depending on the chosen tool, focusing on performance and flexibility:

* It **illustrates** the **process** of **building** a **complex message** by **appending** various **pieces** of data **step-by-step**.
* The code **demonstrates advanced manipulation techniques**, such as **reversing** text or **inserting** content into specific positions.
* It **contrasts** the "**expensive**" nature of standard **`String` concatenation** with the **memory-efficient** approach of a **mutable builder**.
* The program **shows** how to **convert** a completed **`StringBuilder`** back into a standard **`String`** once the manipulation is finished.

---

## Key concepts

* **Immutability versus mutability**: Standard **`String`** objects **cannot be changed** once created (every "change" creates a new object), while **`StringBuilder`** can be **modified directly** in memory.
* **Efficiency**: Using **`StringBuilder` avoids** the **overhead** of **creating** numerous **intermediate objects** during heavy text processing or loops.
* **Method chaining**: The **ability** to **perform multiple operations** (like `append().insert().reverse()`) in a **single**, readable **line** of code.
* **The `toString()` bridge**: The **final step** used to "**solidify**" a **built message** into a **standard `String`** for use in the rest of the application.

---

## View the files

* [Main.java](file://Optimizing%20text%20manipulation/src/Main.java)