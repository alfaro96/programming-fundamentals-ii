# Exercise 2

This exercise takes object-oriented programming to a more descriptive level by utilizing complex attributes, specific array restrictions, and recursive behavior.

---

## Objective

The goal of this task is to implement a `Person` class in Java that models personal data and manages a fixed-size registry of children.

---

## Requirements

### Attributes

* **Personal data**: `String name`, `int age`, `double height`, `double weight`, and `String address`.
* **Complex attribute**: `Person[] children`, an array containing each child a person has.

### Methods

* **Constructor overloading**:
  1. A constructor for parents that initializes the `children` array with a specific size based on a provided number. 
  2. A constructor for childless individuals where the array is initialized with size 0.
* **`addChild(Person child)`**: Adds a child to the internal array. If the array is full, it must display an error message.
* **`getOldestChild()` and `getYoungestChild()`**: Return the child with the maximum or minimum age from the array.
* **`toString()`**: Returns a `String` representation of the person. Since children are also `Person` objects, you must call their `toString()` method recursively.
* **`main(String[] args)`**: A test method located inside the class to verify all functionality.

---

## Evaluation criteria

To successfully complete the exercise, your internal `main` method must perform the following:

1. **Object setup**: Create `Person 1` (2 children capacity), `Person 2` (3 children capacity), and `Person 3` (childless).
2. **Relationship building**: Add new `Person` objects as children to each parent using `addChild()`.
3. **Functional verification**:
  * Display everyone's info.
  * Update `Person 1`'s age and verify it on screen.
  * Show `Person 1`'s children and identify `Person 2`'s oldest and youngest children.
4. **Documentation**: Ensure the code is clean and includes proper Javadoc.

---

## View the files

* [Person.java](file://src/Person.java)