# Implementing inheritance

This example **explores** the fundamental object-oriented concept of **inheritance**, which involves establishing **parent and child relationships** between classes to build logical hierarchies.

---

## What does it do?

The program **amplifies** the basic **`Player`** class by introducing specialized roles, demonstrating how new classes can build upon existing ones:

* It **integrates** four new classes (**`Forward`**, **`Midfielder`**, **`Defender`**, and **`Goalkeeper`**) that act as subclasses of the `Player` superclass.
* The code **presents** how subclasses seamlessly inherit the basic data (like `name`, `age`, and `nationality`) and actions (like `celebrate()`) from their superclass.
* It **introduces** role-specific data for each subclass, such as `goals` for a forward or `saves` for a goalkeeper, proving that a subclass can expand upon its superclass.

---

## Key concepts

* **Code reuse**: A **superclass** contains all **common variables** and **methods**. This prevents you from writing the exact same code in multiple places.
* **Specific behaviors**: Each **subclass** defines its own **concrete variables** and **methods**, allowing for specialized functionality.
* **Inheritance mechanism**: The **`extends`** keyword gives the subclass **access** to the superclass' members **without redefining** them.
* **Superclass and subclass relationships**: The logical structure where the subclass "is a" type of the superclass (e.g., a `Forward` "is a" `Player`).

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Midfielder.java](file://src/playerRoles/Midfielder.java)
* [Defender.java](file://src/playerRoles/Defender.java)
* [Goalkeeper.java](file://src/playerRoles/Goalkeeper.java)
* [Main.java](file://src/Main.java)