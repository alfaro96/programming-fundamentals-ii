# Managing team players with a resizable array

This example **demonstrates** how to **use** an `ArrayList` to **store and manage objects** of **different classes**, **even if** they are **not related through inheritance**.

---

## What does it do?

The program **defines** two classes: `Forward` and `Midfielder`. **Each** class **represents** a **specific role** on a soccer team **and includes** its **own method**:

* `Forward` has a `score()` method.
* `Midfielder` has an `assist()` method.


**Inside** the `Main` class:

* An `ArrayList` is **created with** an **initial capacity** for **3 players**.
* **Several** `Forward` **and** `Midfielder` objects are **added** to the list.
* The program **loops** through the list **using** a **standard index-based** for loop, similar to arrays.
* It **uses** `instanceof` **to check** each **object’s type and calls** the **appropriate method**.
* It **prints out** the **details** of **each player** using their overridden `toString()` methods. 
* Finally, it demonstrates **various** useful `ArrayList` **operations** are **demonstrated**: replacing, inserting, checking for elements, removing, clearing, and checking if the list is empty.

---

## Key concepts

* `ArrayList` **allows** storing **heterogeneous objects** (objects of different types).
* The **list** can **resize dynamically**, even if the initial capacity is smaller.
* You can **use** `instanceof` **and casting** to **call methods specific** to each object’s class.
* A **standard** `for` **loop accesses** list **elements by index**, just like an array.
* `ArrayList` supports **helpful methods** such as:
  * `set(index, element)`, replace an element;
  * `add(index, element)`, insert at a specific position;
  * `contains(element)`, `indexOf(element)`, `get(index)`, search and retrieve;
  * `remove(index)`, `clear()`, `isEmpty()`, remove or reset the list.

---

## View the files

* [Main.java](file://Collections/Using%20resizable%20arrays%20with%20different%20object%20types/src/Main.java)
* [Player.java](file://Collections/Using%20resizable%20arrays%20with%20different%20object%20types/src/playerRoles/Player.java)
* [Forward.java](file://Collections/Using%20resizable%20arrays%20with%20different%20object%20types/src/playerRoles/Forward.java)
* [Midfielder.java](file://Collections/Using%20resizable%20arrays%20with%20different%20object%20types/src/playerRoles/Midfielder.java)
