# Invoking parent constructors and methods

This example **explores** how subclasses can actively communicate with their parent classes to initialize inherited data and reuse existing logic by accessing superclass logic with the `super` keyword.

---

## What does it do?

The program **amplifies** our class hierarchy by making all parent attributes strictly `private`, forcing the subclasses to interact with the superclass constructor properly:

* It **integrates** the `super(arguments)` syntax in the `Forward` class to pass data up the chain and securely initialize the parent's state.
* The code **presents** the difference between an explicit constructor call (as seen in `Defender`) and an implicit one (as seen in `Midfielder`), where Java steps in to help.
* It **introduces** the `super.` syntax in the `Goalkeeper` class to trigger the parent's standard behavior directly from the subclass.

---

## Key concepts

Mastering the `super` keyword is essential for robust object-oriented programming:

* **The `super` keyword**: A special reference that accesses the superclass to invoke its methods or constructors.
* **`super.` (method access)**: Calls a parent method, bypassing any subclass overrides to ensure the original superclass logic is executed.
* **`super(arguments)` (constructor access)**: Invokes a specific parent constructor to handle the initialization of inherited fields.
* **Placement rule**: When calling a parent constructor, `super(arguments)` **must be the first statement** inside the subclass constructor.
* **Implicit call**: If omitted by the developer, Java automatically calls the parent's **default constructor** behind the scenes.

---

## View the files

* [Player.java](file://src/playerRoles/Player.java)
* [Forward.java](file://src/playerRoles/Forward.java)
* [Midfielder.java](file://src/playerRoles/Midfielder.java)
* [Defender.java](file://src/playerRoles/Defender.java)
* [Goalkeeper.java](file://src/playerRoles/Goalkeeper.java)
* [Main.java](file://src/Main.java)