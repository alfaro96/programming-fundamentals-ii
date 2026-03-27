# Declaring potentially dangerous code

This example **demonstrates** how the `throws` keyword **alerts** developers to potential failures and enforces safe coding practices within our player hierarchy.

---

## What does it do?

The program **expands** our existing `Forward` class by adding a method to calculate the player's shooting accuracy.

* It **introduces** a natural division operation that could fail if we try to divide by zero.
* It **declares** the `calculateAccuracy` method as potentially dangerous using `throws ArithmeticException`.
* The `Main` class **attempts** to **invoke** this method with a zero value.
* Because the warning was ignored and the exception is not handled, the program **crashes completely** during execution. This proves that declaring an exception is not enough; it must be handled!

---

## Key concepts

* The `throws` keyword **signals** that a method's code might fail and throw an exception.
* It is placed **immediately after** the method signature.
* Adding `throws` serves as an explicit **warning** for anyone using your method.
* If you invoke a dangerous method, pass invalid data (like a zero for division), and do not handle the risk, the program will crash and disrupt the regular flow.

---

## View the files

* [Forward.java](file://src/playerRoles/Forward.java)
* [Main.java](file://src/Main.java)