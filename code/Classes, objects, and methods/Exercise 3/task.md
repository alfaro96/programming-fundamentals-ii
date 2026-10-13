# Exercise 3

The goal of this exercise is to write a `Book` class in Java that represents a book with its data and the text of its pages. Unlike the previous exercises, you have to decide the parameters and the return type of each method from what it has to do.

### Implementation

Write a `Book` class with the following requirements:

* **Attributes**:
  * `String title`: the title of the book.
  * `String author`: the author of the book.
  * `int numPages`: the number of pages, which must always match the length of `pages`.
  * `int publicationYear`: the year the book was published.
  * `final String isbn`: the *International Standard Book Number*, which identifies the book. It is given its value in the constructor and never changes, so it is declared `final`.
  * `String[] pages`: the text of each page, one page per position, the first one at position 0.
  * `int currentPage`: the position of the page the book is open at, 0 when the book is created.

* **Methods**:
  * **Constructor**: receives the title, the author, the publication year, the ISBN and the array of pages. `numPages` comes from the length of the array, and `currentPage` starts at 0, so neither of them is a parameter.
  * `addPage()`: adds a page with a given text at a given position, and that page and the following ones move forward one position. The position can go from 0, before the first page, to `numPages`, after the last one; for any other position the book does not change. An array cannot change its size, so you have to create a new array, one position longer, and copy the pages into it.
  * `replacePage()`: replaces the text of the page at a given position. If there is no page at that position, the book does not change.
  * `readPage()`: returns the text of the page the book is open at and moves on to the next page. Past the last page, it returns `null`.
  * `goToFirstPage()`: opens the book at its first page again.
  * `removePage()`: removes the page at a given position, and the following pages move back one position. If there is no page at that position, the book does not change. This also requires creating a new array.
  * `concatenateWith()`: given another `Book` by the same author, returns a **new** book with the pages of both, first those of this book and then those of the other. Choose sensible values for the title, publication year and ISBN of the new book. If the authors differ, it returns `null`.
  * `describe()`: returns a `String` with the data of the book, without the text of its pages.
  * `isEqualTo()`: given another `Book`, checks whether both are the same book, which happens when they have the same ISBN, whatever their other data.

Remember that two `String` values are compared with `equals`, not with `==`.

### Recommendations

* **Incremental implementation**: write and test each method on its own before moving on to the next one, making sure each part works before building the rest of the class.

### Evaluation and testing

To complete the exercise, write a `Main` class with a `main` method that performs the following tests:

1. **Class functionality**:
   * Write the `Book` class with all the attributes and methods described.

2. **Testing in the `main` method**:
   * Create a `Book` object and add several pages with `addPage()`, at the beginning, in the middle and at the end.
   * Check `replacePage()` by changing the text of an existing page.
   * Use `readPage()` to go through some pages.
   * Test `removePage()` and check that the remaining pages keep their order.
   * Create other books to test `concatenateWith()` and `isEqualTo()`.

3. **Clean code and documentation**:
   * Make sure the code is clean and legible, with explanatory comments where necessary.
