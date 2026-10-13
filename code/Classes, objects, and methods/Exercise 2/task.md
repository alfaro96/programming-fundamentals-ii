# Exercise 2

The goal of this exercise is to write a `Person` class in Java. Compared to the previous exercise, it leaves more to your interpretation of how each method works, uses an array of objects as an attribute and has to handle some error cases.

### Implementation

Write a `Person` class with the following requirements:

* **Attributes**:
  * `String name`: the name of the person.
  * `int age`: the age of the person, in years.
  * `double height`: the height of the person, in meters.
  * `double weight`: the weight of the person, in kilograms.
  * `String address`: the address of the person.
  * `Person[] children`: the children of the person, who are people too. The array has a fixed size and is filled from its first position, so the empty positions, all at the end, hold `null`.

* **Methods**:
  * **Constructors**:
    * A constructor that receives `name`, `age`, `height`, `weight`, `address` and the number of children the person can have, which is the size of the array. No child has been added yet.
    * A constructor that receives only `name`, `age`, `height`, `weight` and `address`, for a person without children. The array of children must be created anyway, with no positions, never left as `null`.
  * `addChild()`: given a `Person`, stores it in the first empty position of the array of children. The array never grows: if it is full, the method prints an error message and the child is not added.
  * `getOldestChild()` and `getYoungestChild()`: return the oldest or the youngest child, or `null` if the person has no children.
  * `describe()`: returns a `String` with all the data of the person, followed by the data of each child. Since children are `Person` objects too, the data of each one can be obtained by calling `describe()` on it.

### Recommendations

* **Incremental implementation**: write and test each method on its own before moving on to the next one, making sure each part works before building the rest of the class.
* **Building the text**: `describe()` joins one piece of text per child, so a `StringBuilder` is a better fit than `+`.

### Evaluation and testing

To complete the exercise, write a `Main` class with a `main` method that performs the following tests:

1. **Object creation**:
   * Create two `Person` objects: `Person 1`, with room for two children, and `Person 2`, with room for three.
   * Create new `Person` objects and add them as children of each one with `addChild()`.
   * Create a third person, `Person 3`, with the constructor for a person without children.

2. **Functional verification**:
   * Print the data of each person.
   * Change the age of `Person 1` and print its data again to check the change.
   * Go through the array of children of `Person 1` and print each one.
   * Print the youngest and the oldest child of `Person 2`.
   * Try to add a child to a person whose array is full, and check that the error message is printed.

3. **Clean code and documentation**:
   * Make sure the code is clean and legible, with explanatory comments where necessary.
