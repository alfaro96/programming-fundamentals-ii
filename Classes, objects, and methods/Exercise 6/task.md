# Exercise 6

This exercise focuses on managing object collections that can grow dynamically. You will implement a `Library` class that stores `Book` objects and provides a navigation system to "read" through them one by one.

---

## Objective

The goal of this exercise is to define a `Library` class in Java that represents a container for books, handling manual array resizing and navigation state.

---

## Requirements

### Attributes

* **`String name`**: The name of the library.
* **`Book[] books`**: An array that stores the library's collection.
* **`int currentBookIndex`**: A pointer representing the book currently being read. It defaults to -1 to indicate no book is selected.

### Methods

* **Constructor**: Takes a name and initial capacity. The library starts without any books.
* **`addBook(Book book)`**: Adds a book to the first available spot. **Crucial**: If the array is full, you must double its size by creating a new array and copying existing values.
* **`getCurrentBook()`**: Returns the book at the current index.
* **`nextBook()`**: Advances the index and returns the next book. Returns `null` if the end of the collection is reached.
* **`previousBook()`**: Moves the index back and returns the previous book. Returns `null` if it goes before the first book.
* **`toString()`**: Returns basic info about the library (name and total books), excluding the content of the books themselves.

---

## Evaluation criteria

To successfully complete the exercise, you must implement a `main` method within the `Library` class to:

1. **Creation**: Instantiate a library with a small initial capacity (e.g., 2).
2. **Growth**: Add enough books to trigger the array-doubling logic.
3. **Navigation**: Use `nextBook()` and `previousBook()` to traverse the collection and verify that edge cases (start and end of list) are handled.
4. **Documentation**: Provide clear Javadoc and maintain clean code standards.

---

## View the files

* [Library.java](src/Library.java)
