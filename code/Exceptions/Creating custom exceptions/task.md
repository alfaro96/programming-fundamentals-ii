# Creating custom exceptions

This example **demonstrates** how to create your own domain-specific exception objects by extending Java's base exception classes.

---

## What does it do?

The program **introduces** two brand-new exception classes specific to our football simulation:

1.  **`PlayerInjuredException`**: Extends `RuntimeException`. It represents an unexpected event (unchecked).
2.  **`TransferDeclinedException`**: Extends `Exception`. It represents a formal business rule failure that must be handled (checked).

The `Forward` class **uses** these new objects to signal problems. When an error occurs, it instantiates the custom exception and passes a specific message to its constructor (`super()`).
Finally, the `Main` class **catches** these custom errors and uses `toString()` to print both the name of the custom exception and the message we provided.

---

## Key concepts

* **Domain objects**: Since exceptions are objects, we can build custom ones for specific application errors, making our code much easier to read and debug.
* **Checked versus unchecked**:
  * Use `extends Exception` to enforce mandatory handling (`TransferDeclinedException`).
  * Use `extends RuntimeException` for optional handling (`PlayerInjuredException`).
* **Custom messages**: By passing a string to `super()` in your custom constructor, you store an error message. Invoking `toString()` on the caught exception will print the exception's exact class name alongside this message.

---

## View the files

* [PlayerInjuredException.java](file://src/exceptions/PlayerInjuredException.java)
* [TransferDeclinedException.java](file://src/exceptions/TransferDeclinedException.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Main.java](file://src/Main.java)
