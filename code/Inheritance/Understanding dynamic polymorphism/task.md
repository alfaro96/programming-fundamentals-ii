# Mastering dynamic polymorphism

The true power of object-oriented programming lies in **dynamic polymorphism**. This mechanism allows your code to stay flexible and adapt to different objects even when using a generic reference.

---

## What does it do?

This exercise focuses on how Java resolves behavior at the very last second:

* We use **method overriding** to allow subclasses like `Forward` and `Goalkeeper` to provide their own **specific implementation** of a common action.
* You will observe a **runtime decision** in action: even though our references are of type `Player`, the program executes the method belonging to the **actual object type in memory**.

---

## Key concepts

Dynamic polymorphism is driven by two main rules:

* **Runtime decision**: Unlike overloading, the specific method to run is determined **while the program is running**, not during compilation. It depends entirely on the **actual object** being referenced.
* **Dynamic binding**: This offers **massive adaptability**. You can write a single line of code (like `player.playMatch()`) that triggers **completely different behaviors** depending on which specific player is on the field at that moment.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Goalkeeper.java](file://src/playerRoles/Goalkeeper.java)
* [Main.java](file://src/Main.java)
