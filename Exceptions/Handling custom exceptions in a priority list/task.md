# Handling custom exceptions in a priority list

This example **demonstrates** how to define and use **custom exceptions** to improve clarity and error handling in your application.

---

## What does it do?

The code **defines** a `PriorityList` class **where elements** are **added and removed based on** their **priority**. It **uses custom exceptions** to **handle error conditions** in a **clearer and** more **meaningful way**:

* `ListFullException`: **thrown** when **adding** to a **full list**
* `EmptyListException`: **thrown** when trying to **remove** from an **empty list**
* `ElementNotFoundException`: **thrown** when **removing** a **non-existent item** (unchecked)

The `Main` class shows how these exceptions are thrown and caught during usage.

---

## Key concepts

* **Custom exceptions** make your **code more descriptive and easier** to **debug**.
* **Extend** `Exception` for **checked exceptions** and `RuntimeException` for **unchecked exceptions**.
* **Catch checked exceptions** using `try` and `catch` block.
* **Unchecked exceptions do not require** `throws` **or** a `try` and `catch` block, **but may** still cause **runtime errors**.

---

## View the files

- [Main.java](file://Exceptions/Handling%20custom%20exceptions%20in%20a%20priority%20list/src/Main.java)
- [PriorityList.java](file://Exceptions/Handling%20custom%20exceptions%20in%20a%20priority%20list/src/prioritylist/PriorityList.java)
- [Record.java](file://Exceptions/Handling%20custom%20exceptions%20in%20a%20priority%20list/src/prioritylist/Record.java)
- [ListFullException.java](file://Exceptions/Handling%20custom%20exceptions%20in%20a%20priority%20list/src/exceptions/ListFullException.java)
- [EmptyListException.java](file://Exceptions/Handling%20custom%20exceptions%20in%20a%20priority%20list/src/exceptions/EmptyListException.java)
- [ElementNotFoundException.java](file://Exceptions/Handling%20custom%20exceptions%20in%20a%20priority%20list/src/exceptions/ElementNotFoundException.java)
