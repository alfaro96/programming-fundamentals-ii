# Defining abstract blueprints

Sometimes a class represents a concept so broad that it shouldn't exist directly as an object. This exercise explores how to use abstract classes to create **strict blueprints** for your object hierarchy.

---

## What does it do?

This code implements a robust structure using incomplete templates:

* You will see the `Player` class acting as a base template, **declared using the `abstract` keyword**.
* We demonstrate **mixed content**: the `Player` class contains both fully implemented methods (like `train()`) and incomplete ones (like `calculateBonus()`).
* Since a generic player doesn't know how to calculate a bonus, it forces its subclasses to provide the logic. The code enforces this **strict contract** on the `Forward` subclass.

---

## Key concepts

Mastering abstract classes requires understanding their specific restrictions:

* **Instantiation**: You **cannot instantiate** an abstract class directly to create an object. They are just templates.
* **Abstract methods**: They are declared **without a body** (no implementation) to define a required behavior. Additionally, these methods **cannot be marked as `static`, nor can they have constructors**.
* **The contract**: Concrete subclasses **must override and implement** all inherited abstract methods.
* **Exceptions**: The only exception to the implementation rule is if the subclass itself is also **declared as `abstract`**.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [FieldPlayer.java](file://src/playerRoles/FieldPlayer.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Main.java](file://src/Main.java)
