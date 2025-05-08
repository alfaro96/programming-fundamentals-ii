# Printing player details without wildcards

This example **demonstrates** a **limitation** of **generics** when **passing lists** of **subclass objects** to **methods expecting** a **list** of their **superclass type**.

---

## What does it do?

The program **defines** a class `Forward` that extends the base class `Player`.

In the `Main` class:

* An `ArrayList<Forward>` is **created** to hold only forward players.
* An **attempt** is made to **pass** the **list** of **forwards** to a **method** that **expects** an `ArrayList<Player>`.

**Although** the **objects in** the **list** are **valid** `Player` **instances**, the **compiler** produces an **error because generics** are **invariant**, `ArrayList<Forward>` is **not** a **subtype** of `ArrayList<Player>`.

---

## Key concepts

* **Generic types** are **invariant**, a **list** of a **subclass** is **not considered** a **list** of the **superclass**.
* **Trying** to **pass** an `ArrayList<Forward>` to a **method expecting** `ArrayList<Player>` will **result** in a **compile-time error**.
* This **prepares** the ground **for using** wildcards**, which allow flexibility in generic method parameters.

---

## View the files

* [Main.java](file://Collections/Printing%20player%20details%20without%20wildcards/src/Main.java)
* [Player.java](file://Collections/Printing%20player%20details%20without%20wildcards/src/playerRoles/Player.java)
* [Forward.java](file://Collections/Printing%20player%20details%20without%20wildcards/src/playerRoles/Forward.java)
* [Midfielder.java](file://Collections/Printing%20player%20details%20without%20wildcards/src/playerRoles/Midfielder.java)
