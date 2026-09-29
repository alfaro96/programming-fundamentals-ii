# Exercise 6

The goal of this exercise is to define the `Library` class, which represents a library containing several `Book` objects. To complete this exercise, you must use the `Book` class.

### Implementation

Implement an `Library` class with the following requirements:

* **Attributes**:
  * `String name`: The name of the library.
  * `Book[] books`: An array containing every `Book` within the library.
  * `int currentBookIndex`: An index representing the book currently being read. By default, this starts at -1, indicating that no book is currently selected.

* **Methods**:
  * **Constructor**:
    * A constructor that takes a name and the initial maximum capacity of books. By default, the library starts without any books.
  * `addBook()`: Adds a given book to the first empty position in the array. If the array is full, its size must be doubled before adding the book. Since arrays are immutable, you must create a new, larger array and copy the existing values.
  * `getCurrentBook()`: Returns the `Book` specified by the current index.
  * `nextBook()`: Moves the current index to the next book and returns it. If no more books exist, it should return a null value.
  * `previousBook()`: Moves the current index to the previous book and returns it. If no books exist before the current one, it should return a null value.
  * `toString()`: Returns a human-legible `String` representing the library's information. This method should not print the books themselves, only the information about the library.

### Recommendations

* **Incremental implementation**: It is highly recommended to implement and test each method individually before moving on to the next one, ensuring each part works correctly before building the rest of the class.

### Evaluation and testing

To successfully complete the exercise, implement a `main` method to perform the following tests:

1. **Class functionality**:
   * Create the `Library` class with all specified attributes, constructors, and methods.

2. **Main method testing**:
   * Create a library and several books.
   * Add books to the library, ensuring the initial size is small enough to trigger the array-doubling logic.
   * Move through the library using navigation methods and verify that edge cases (reaching non-existent books) are handled properly.

3. **Clean code and documentation**:
    * Ensure that the code is clean, legible, and includes explanatory comments where necessary.
