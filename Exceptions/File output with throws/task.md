# Save to file with `throws`

This example **demonstrates** how to **declare exceptions** using the `throws` keyword.

---

## What does it do?

The code **writes** a **message to** a **file** using `FileWriter`. **Since** file operations may cause an `IOException`, the **method uses** the `throws` keyword to **delegate responsibility** to the **caller**. The **caller** then **uses** a `try-catch` block to **handle** the **exception** gracefully if it occurs.

---

## Key concepts

* The `throws` keyword **declares** that a **method** might **throw** an **exception**. It is used to **delegate error handling** to the calling method.
* The **calling method** can then **handle** the exception **using** `try` and `catch` block.
* This **example** deals with a **checked exception**: `IOException`.

---

## View the file

[Main.java](file://Exceptions/Save%20to%20file%20with%20throws/src/Main.java)
