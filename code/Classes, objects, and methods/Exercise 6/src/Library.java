/**
 * Represents a library: a name and an array of {@link Book} objects that can be gone
 * through one by one, forward or backward.
 * <p>
 * The books fill the array from the start, so the empty positions, all at the end, hold
 * {@code null}. When the array is full, adding a book replaces it with one twice as long.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Book
 */
class Library {

    /**
     * The name of the library.
     */
    String name;

    /**
     * The books of the library, with {@code null} in the positions not yet filled.
     */
    Book[] books;

    /**
     * The position of the book currently selected, or &minus;1 if none is.
     */
    int currentBookIndex;

    /**
     * Creates a library with no books and no book selected.
     *
     * @param name the name of the library.
     * @param capacity the number of books the library has room for at first.
     */
    Library(String name, int capacity) {
        this.name = name;
        this.books = new Book[capacity];
        this.currentBookIndex = -1;
    }

    /**
     * Counts the books in the library, which may be fewer than the positions of the array.
     *
     * @return the number of books.
     */
    int countBooks() {
        int count = 0;
        for (Book book : this.books) {
            if (book != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Adds a book in the first empty position of the array.
     * <p>
     * If the array is full, it is first replaced by a new one twice as long, at least one
     * position long, with the same books in the same positions.
     *
     * @param book the book to add.
     */
    void addBook(Book book) {
        int count = this.countBooks();
        if (count == this.books.length) {
            Book[] larger = new Book[Math.max(1, 2 * this.books.length)];
            for (int i = 0; i < this.books.length; i++) {
                larger[i] = this.books[i];
            }
            this.books = larger;
        }
        this.books[count] = book;
    }

    /**
     * Returns the book currently selected.
     *
     * @return the current book, or {@code null} if none is selected.
     */
    Book getCurrentBook() {
        if (this.currentBookIndex < 0) {
            return null;
        }
        return this.books[this.currentBookIndex];
    }

    /**
     * Selects the next book and returns it. If there is no next book, the selection does
     * not change.
     *
     * @return the next book, or {@code null} if the current one is the last.
     */
    Book nextBook() {
        int next = this.currentBookIndex + 1;
        if (next >= this.books.length || this.books[next] == null) {
            return null;
        }
        this.currentBookIndex = next;
        return this.books[next];
    }

    /**
     * Selects the previous book and returns it. If there is no previous book, the
     * selection does not change.
     *
     * @return the previous book, or {@code null} if the current one is the first or none is
     *         selected.
     */
    Book previousBook() {
        if (this.currentBookIndex <= 0) {
            return null;
        }
        this.currentBookIndex--;
        return this.books[this.currentBookIndex];
    }

    /**
     * Returns the data of the library, without those of its books, such as
     * {@code "City Library: 3 books, room for 4"}.
     *
     * @return the name, the number of books and the length of the array.
     */
    String describe() {
        return this.name + ": " + this.countBooks() + " books, room for " + this.books.length;
    }
}
