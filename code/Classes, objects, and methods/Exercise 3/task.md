# Exercise 3

The goal of this exercise is to define the `Book` class in Java, representing a generic book with specific attributes and methods. Unlike previous exercises, you must determine the appropriate arguments and return types for each method based on the logic of the exercise.

### Implementation

Implement a `Book` class with the following requirements:

* **Attributes**:
  * `String title`: The title of the book.
  * `String author`: The author of the book.
  * `int numPages`: Total number of pages.
  * `int publicationYear`: Year the book was published.
  * `String ISBN`: The *International Standard Book Number* code.
  * `String[] pages`: An array where each element represents the text of one page.
  * `int currentPage`: The page currently opened (defaults to 0).

* **Methods**:
  * **Constructor**: A constructor that takes all relevant values. Note that default values should not be passed as arguments.
  * `addPage()`: Adds a new page at a specified position. All subsequent pages must move forward one position. Since arrays are immutable in size, you must create a larger array and copy the values.
  * `replacePage()`: Replaces the content of a specified page with new text.
  * `readPage()`: Returns the content of the current page and advances to the next one.
  * `goToFirstPage()`: Resets the `currentPage` to the first page of the book.
  * `removePage()`: Removes a page at a specified position, moving all subsequent pages backward. This also requires creating a new array.
  * `concatenateWith()`: Given another `Book`, returns a **new** book containing the pages of both, provided they share the same author.
  * `toString()`: Returns a `String` with the book's information, excluding the content of the pages.
  * `equals()`: Overrides the default method to check if two books are equal based solely on their `ISBN`.

### Recommendations

* **Incremental implementation**: It is highly recommended to implement and test each method individually before moving on to the next one, ensuring each part works correctly before building the rest of the class.

### Evaluation and testing

To successfully complete the exercise, implement a `main` method to perform the following tests:

1. **Functionality**:
   * Create the `Book` class with all defined attributes and methods.

2. **Main method testing**:
   * Create a `Book` object and add several pages using `addPage()`.
   * Verify the `replacePage()` method by modifying existing content.
   * Use `readPage()` to iterate through some pages.
   * Test `removePage()` and verify the array structure remains consistent.
   * Create a second book to test the `concatenateWith()` and `equals()` methods.

3. **Clean code and documentation**:
   * Ensure the code is clean, legible, and includes explanatory comments where necessary.
