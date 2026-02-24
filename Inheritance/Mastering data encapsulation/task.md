# Mastering data encapsulation

This example **explores** the concept of **encapsulation**, which is the practice of hiding the internal state of an object and requiring all interaction to be performed through an object's methods. We achieve this by restricting access through **modifiers**.

---

## What does it do?

The program **amplifies** our understanding of class hierarchies by dividing our classes into **different packages** (`playerRoles`, `externalClub`, `matchAnalysis`). This physical separation triggers the different visibility rules:

* It **integrates** four distinct attributes inside the `Player` class, each using a different access modifier (`public`, `protected`, default, and `private`).
* The code **presents** scenarios where subclasses inside the same package (`Forward`) have different privileges compared to subclasses in other packages (`LoanedPlayer`).
* It **introduces** an external class (`PerformanceAnalyst`) to demonstrate how data is completely locked down for outside entities, protecting sensitive information like medical conditions.

---

## Key concepts

Java provides four levels of access control to enforce encapsulation:

* **`public`**: The most permissive level. The attribute or method can be accessed from **any class**, regardless of the package.
* **`protected`**: Accessible within the **same package**, and also by any **subclass** even if that subclass is in a different package.
* **Default (`package`-`private`)**: If you don't write a modifier, the attribute is only accessible to classes from the **same package**. It is completely hidden from classes in a different package.
* **`private`**: The most restrictive level. The attribute is only visible from the **same class** where it is declared.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Midfielder.java](file://src/playerRoles/Midfielder.java)
* [Defender.java](file://src/playerRoles/Defender.java)
* [Goalkeeper.java](file://src/playerRoles/Goalkeeper.java)
* [LoanedPlayer.java](file://src/externalClub/LoanedPlayer.java)
* [PerformanceAnalyst.java](file://src/matchAnalysis/PerformanceAnalyst.java)
* [Main.java](file://src/Main.java)