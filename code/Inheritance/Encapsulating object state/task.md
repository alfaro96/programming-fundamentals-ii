# Encapsulating object state

This example **explores** how to safely interact with an object's hidden data by using special methods commonly known as **accessors** (getters) and **mutators** (setters).

---

## What does it do?

The program **amplifies** our existing class hierarchy by fully implementing data hiding and controlled access:

* It **integrates** strictly `private` attributes across all classes, ensuring that variables like `name` or `goals` cannot be altered directly from the outside.
* The code **presents** the use of public methods to read and write these values safely from the `Main` class.
* It **introduces** data validation within the setter methods (for example, preventing a negative `age` or negative `goals`), demonstrating the primary advantage of this approach over using public variables.

---

## Key concepts

* **Data hiding**: Keeping class attributes `private` to protect the internal state of the object from unintended interference.
* **Accessor (getter)**: A `public` method whose sole purpose is to **return** the current value of a private attribute without changing it.
* **Mutator (setter)**: A `public` method designed to **update** or modify a private attribute safely.
* **Data validation**: Setters act as gatekeepers, allowing developers to include logic (like `if` statements) to reject invalid data before it corrupts the object's state.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Midfielder.java](file://src/playerRoles/Midfielder.java)
* [Defender.java](file://src/playerRoles/Defender.java)
* [Goalkeeper.java](file://src/playerRoles/Goalkeeper.java)
* [Main.java](file://src/Main.java)
