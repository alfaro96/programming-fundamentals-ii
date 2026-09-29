# Exercise 2

A person refers to the concept of a `Person` and the attributes or behaviors that define it. The goal of this exercise is to define a `Person` class in Java. Compared to previous exercises, this requires more interpretation regarding how methods work, utilizes complex attributes like arrays of objects, and includes specific restrictions and error checking.

### Implementation

Implement a `Person` class with the following requirements:

* **Attributes**:
  * `String name`: The name of the person.
  * `int age`: The age of the person.
  * `double height`: The height in meters.
  * `double weight`: The weight in kilograms.
  * `String address`: The address of the person.
  * `Person[] children`: An array containing each child a person has.

* **Methods**:
  * **Constructors**:
    * A constructor that takes `name`, `age`, `height`, `weight`, `address`, and the number of children.
    * A constructor that only takes `name`, `age`, `height`, `weight`, and `address`. The person is assumed to be childless and the array must be initialized appropriately.
  * `addChild()`: A method that, given a `Person` as an argument, adds it to the children array. The array size is fixed; if it is full, the method should show an error.
  * `getOldestChild()` and `getYoungestChild()`: Methods that return the oldest or youngest child in the children array.
  * `toString()`: A method that returns a `String` representation of a person, including all information and the information of each child. Note: Since children are also `Person` objects, you can access their `toString()` method recursively.

### Recommendations

* **Incremental implementation**: It is highly recommended to implement and test each method individually before moving on to the next one, ensuring each part works correctly before building the rest of the class.

### Evaluation and testing

To successfully complete the exercise, implement a `main` method to perform the following tests:

1. **Object creation and setup**:
   * Create two `Person` objects: `Person 1` with two children and `Person 2` with three children.
   * Create new `Person` objects and add them as children to each parent using `addChild()`.
   * Create a third person, `Person 3`, using the childless constructor.

2. **Functional verification**:
   * Display the information of each person on the screen.
   * Change the age of `Person 1` and verify the change on screen.
   * Obtain the children array of `Person 1` and show it.
   * Obtain and display the youngest and oldest child of `Person 2`.

3. **Clean code and documentation**:
   * Ensure that the code is clean, legible, and includes explanatory comments where necessary.
