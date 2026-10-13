# Exercise 6

The goal of this exercise is to write a `Library` class that represents a library holding several `Book` objects. It uses the `Book` class of Exercise 3, which is provided.

### Implementation

Write a `Library` class with the following requirements:

* **Attributes**:
  * `String name`: the name of the library.
  * `Book[] books`: the books of the library. The array is filled from its first position, so the empty positions, all at the end, hold `null`.
  * `int currentBookIndex`: the position of the book currently selected. It starts at -1, which means that no book is selected.

* **Methods**:
  * **Constructor**: receives the name of the library and the number of books it has room for at first. The library starts without books.
  * `addBook()`: adds a book in the first empty position of the array. If the array is full, it first has to be replaced by one twice as long: since an array cannot change its size, create a new, longer array and copy the books into it, in the same positions.
  * `getCurrentBook()`: returns the book currently selected, or `null` if none is.
  * `nextBook()`: selects the next book and returns it. If there are no more books, it returns `null` and the selection does not change.
  * `previousBook()`: selects the previous book and returns it. If there is no book before the current one, or no book is selected, it returns `null` and the selection does not change.
  * `describe()`: returns a `String` with the data of the library, including its name and its number of books, but not the data of the books themselves.

### Recommendations

* **Incremental implementation**: write and test each method on its own before moving on to the next one, making sure each part works before building the rest of the class.

### Evaluation and testing

To complete the exercise, write a `Main` class with a `main` method that performs the following tests:

1. **Class functionality**:
   * Write the `Library` class with all the attributes, constructors and methods described.

2. **Testing in the `main` method**:
   * Create a library and several books.
   * Add the books to the library, with a starting size small enough for the array to have to double.
   * Go through the library with the navigation methods, in both directions, and check that reaching either end is handled properly.

3. **Clean code and documentation**:
   * Make sure the code is clean and legible, with explanatory comments where necessary.
