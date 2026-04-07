# Delegating responsibility with `throws`

This example **demonstrates** how to delegate the responsibility of handling an exception using the `throws` keyword across multiple classes.

---

## What does it do?

The program **illustrates** a chain reaction of method calls within our player hierarchy:

1.  **`Player` class:** Contains a method that calculates fitness using division. It warns that it might fail with `throws ArithmeticException`.
2.  **`Forward` class:** Calls the `Player`'s method. Instead of handling the risk with a `try` and `catch`, it decides to pass the responsibility up the chain by also adding `throws ArithmeticException` to its own method signature.
3.  **`Main` class:** Acts as the final caller. Since the warning has been passed all the way up to it, `Main` takes responsibility and wraps the call in a `try` and `catch` block to prevent a crash.

---

## Key concepts

* **The `throws` declaration**: Used to declare that a method may throw an exception, notifying the caller that they should handle it.
* **No direct handling**: Using `throws` does *not* resolve or catch the exception within the current method.
* **Passing the buck**: If a method doesn't want to deal with an error, it can use `throws` to force the calling method to take responsibility.
* **The final stop**: Eventually, some part of the program (usually the main execution flow) must use `try` and `catch` to handle the error, or the program will crash.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Main.java](file://src/Main.java)