# Redefining inherited behaviors: Overloading vs Overriding

This example **explores** the crucial differences between defining multiple options for a behavior and completely changing an inherited behavior.

---

## What does it do?

The program **amplifies** our class hierarchy by introducing different ways methods can share names:

* It **integrates** two versions of the `train` method in the base class, showing how a class can handle the same action differently depending on the inputs provided.
* The code **presents** the `playMatch()` method, which is defined in the parent class but completely redefined in each subclass to match their specific roles on the pitch.

---

## Key concepts

It is essential to distinguish between these two object-oriented mechanisms:

* **Method overloading**: Defining multiple methods with the **same name** but **different signatures** (different parameters) within a single class.
* **Method overriding**: Allowing a subclass to provide a **specific implementation** for a method that is already defined in its superclass.
* **Signature rule**: Unlike overloading, an overriding method must keep the **exact same signature** (name and parameters) as the superclass' method.
* **Purpose**: Overriding allows subclasses to **tailor inherited actions** to their specific needs, which is the foundation of polymorphism.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Midfielder.java](file://src/playerRoles/Midfielder.java)
* [Defender.java](file://src/playerRoles/Defender.java)
* [Goalkeeper.java](file://src/playerRoles/Goalkeeper.java)
* [Main.java](file://src/Main.java)