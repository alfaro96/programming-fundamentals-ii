# Implementing object casting

Understanding how to navigate the class hierarchy is a **fundamental skill in object-oriented programming**. This exercise focuses on how object references can be **converted up and down the hierarchy chain**.

---

## What does it do?

This code provides a hands-on demonstration of **polymorphism** and **reference conversions**:

* We compare object casting directly with **primitive type casting** (like converting a `double` to an `int`) to clearly illustrate the concepts of **safety and risk**.
* You will see an **implicit upcast** in action, proving how a `Forward` can be seamlessly treated as a generic `Player`.
* Finally, it highlights the necessity of an **explicit downcast** to access subclass-specific methods like `scoreGoal()`, reinforcing that the **underlying object in memory never changes** during the cast.

---

## Key concepts

Mastering the rules of upcasting and downcasting is essential to **avoid runtime errors**:

* **Upcasting**: Moving **up** the class hierarchy. It is **safe and implicit** because a subclass is always a valid instance of its superclass.
* **Downcasting**: Moving **down** the class hierarchy. It carries **risk** and must be **explicit** because the reference might point to a different subclass entirely.
* **Object identity**: Casting does not alter the actual object. **No new object is created**; the same instance is simply referenced with a different type to gain access to specific methods and properties.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Main.java](file://src/Main.java)