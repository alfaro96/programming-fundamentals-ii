# Exercise 3

This task elevates your object-oriented skills by requiring you to manage dynamic content within a static structure. You will handle array resizing, state tracking, and cross-object validation.

---

## Objective

The goal of this exercise is to define a `Book` class in Java that represents a generic book. You must determine the appropriate arguments and return types for each method based on the described logic.

---

## Requirements

### Attributes

* **Metadata**: `String title`, `String author`, `int publicationYear`, and `String ISBN` .
* **Content**: `int numPages` (total count), `String[] pages` (text content of each page), and `int currentPage` (the page currently opened, defaulting to 0) .

### Methods

* **Constructor**: Accepts all relevant metadata and content. **Note**: Default values like `currentPage` should not be passed as arguments.
* **`addPage(int position, String content)`**: Adds a new page at a specific index. You must create a larger array and shift subsequent pages forward.
* **`replacePage(int position, String text)`**: Overwrites the content of a specified page.
* **`readPage()`**: Returns the content of the `currentPage` and advances the pointer to the next one.
* **`goToFirstPage()`**: Resets the `currentPage` to 0.
* **`removePage(int position)`**: Deletes a page, shifts subsequent pages backward, and resizes the array.
* **`concatenateWith(Book other)`**: Returns a **new** `Book` containing pages from both books, but only if they share the same author.
* **`toString()`**: Returns a summary of the book (excluding page content).
* **`equals(Object o)`**: Overrides the default behavior to compare books based solely on their `ISBN`.

---

## Evaluation criteria

To successfully complete the exercise, your internal `main` method must:

1. **Creation**: Initialize a `Book` and populate it using `addPage()`.
2. **Manipulation**: Verify `replacePage()` and `removePage()` while ensuring the array remains consistent.
3. **Iteration**: Use `readPage()` to traverse the content.
4. **Integration**: Create a second book to test `concatenateWith()` and `equals()`.
5. **Clean code**: Provide full Javadoc documentation and maintain legibility.

---

## View the files

* [Book.java](file://src/Book.java)