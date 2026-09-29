# Throwing exceptions manually

This example **demonstrates** how to actively protect your code by creating and launching custom error objects using the `throw` keyword.

---

## What does it do?

The program **expands** our `Forward` class by adding a method to update the player's energy level. However, energy cannot be negative in our simulation!

* It **evaluates** the input value. If the value is invalid (e.g., `-15`), the code refuses to continue.
* It **instantiates** a new `IllegalArgumentException` object, passing a highly specific error message to its constructor.
* It uses the **`throw`** keyword to physically launch this object, immediately disrupting the program's flow.
* The `Main` class **catches** this specific object and prints the custom message we defined.

---

## Key concepts

* **Forcing a failure:** Sometimes, Java doesn't know an error occurred (like a negative energy level), so we must manually trigger an exception during the program's execution.
* **The `throw` keyword:** Placed inside a method body to physically launch the error and stop the current execution.
* **Instantiating the error:** Because exceptions are objects, we must create a new instance to throw it (using the `new` keyword).
* **Descriptive messages:** The exception instance typically receives a string argument in its constructor to define the exact error message we want to display.

---

## View the files

* [Forward.java](file://src/playerRoles/Forward.java)
* [Main.java](file://src/Main.java)