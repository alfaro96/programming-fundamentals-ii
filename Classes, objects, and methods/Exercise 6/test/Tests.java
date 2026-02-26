import org.junit.Test;
import static org.junit.Assert.*;

/**
 * {@link Library} test suite.
 * <p>
 * This class performs unit testing on the {@link Library} class to verify the
 * correctness of book management, dynamic array resizing, navigation, and
 * string representation.
 * </p>
 * <p><b>Covered scenarios:</b></p>
 * <ul>
 * <li>Constructor – {@code currentBookIndex} defaults to {@code -1}; empty array with given capacity.</li>
 * <li>{@link Library#addBook(Book)} – books fill available slots sequentially.</li>
 * <li>{@link Library#addBook(Book)} – array doubles in size when full.</li>
 * <li>{@link Library#getCurrentBook()} – returns the book at the current index.</li>
 * <li>{@link Library#nextBook()} – advances the index and returns the next book.</li>
 * <li>{@link Library#nextBook()} – returns {@code null} past the last book.</li>
 * <li>{@link Library#previousBook()} – moves the index backward and returns the previous book.</li>
 * <li>{@link Library#previousBook()} – returns {@code null} when already at the first book.</li>
 * <li>{@link Library#toString()} – contains library name and book count; does not list book details.</li>
 * </ul>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Library
 * @see Book
 */
public class Tests {

    /**
     * Creates a minimal {@link Book} with a single placeholder page.
     *
     * @param title the book title.
     * @param isbn the unique {@code ISBN} used to identify this book.
     * @return a ready-to-use {@link Book} instance.
     */
    private Book makeBook(String title, String isbn) {
        return new Book(title, "Generic Author", 2020, isbn, new String[]{"Page content"});
    }

    /**
     * Verifies that the constructor sets the library {@code name}, initializes the
     * {@code books} array to the given capacity, and sets {@code currentBookIndex} to {@code -1}.
     */
    @Test
    public void testConstructor() {
        Library lib = new Library("City Library", 3);

        assertEquals("Library name must match", "City Library", lib.name);
        assertNotNull("Books array must not be null", lib.books);
        assertEquals("Books array capacity must match", 3, lib.books.length);
        assertEquals("currentBookIndex must default to -1", -1, lib.currentBookIndex);
    }

    /**
     * Verifies that {@link Library#addBook(Book)} places books in the first available
     * slot sequentially.
     * <p>
     * <b>Scenario:</b> Add two books to a library with capacity {@code 3}.
     * Both books must be stored at indices {@code 0} and {@code 1} respectively.
     * </p>
     */
    @Test
    public void testAddBookStoresBook() {
        Library lib = new Library("Test Library", 3);
        Book book1 = makeBook("Book One", "ISBN-1");
        Book book2 = makeBook("Book Two", "ISBN-2");

        lib.addBook(book1);
        lib.addBook(book2);

        assertNotNull("books[0] must not be null after first addBook()", lib.books[0]);
        assertNotNull("books[1] must not be null after second addBook()", lib.books[1]);
        assertEquals("books[0] title must be 'Book One'", "Book One", lib.books[0].title);
        assertEquals("books[1] title must be 'Book Two'", "Book Two", lib.books[1].title);
    }

    /**
     * Verifies that the {@code books} array doubles in size when it is full and a new
     * book is added.
     * <p>
     * <b>Scenario:</b> Create a library with capacity {@code 2}. Add {@code 3} books –
     * the third insertion must trigger the doubling, making the array length ≥ {@code 4},
     * and the new book must be stored successfully.
     * </p>
     */
    @Test
    public void testAddBookDoublesArray() {
        Library lib = new Library("Small Library", 2);

        lib.addBook(makeBook("Book A", "ISBN-A"));
        lib.addBook(makeBook("Book B", "ISBN-B"));

        int sizeBeforeDoubling = lib.books.length;
        assertEquals("Array should be full (length 2) before third insertion", 2, sizeBeforeDoubling);

        lib.addBook(makeBook("Book C", "ISBN-C")); // triggers doubling

        assertTrue("Array length must at least double after overflow", lib.books.length >= 4);
        assertNotNull("Third book must be stored after doubling", lib.books[2]);
        assertEquals("Third book title must be 'Book C'", "Book C", lib.books[2].title);
    }

    /**
     * Verifies that {@link Library#getCurrentBook()} returns the book at the current
     * index after the index has been set by navigation.
     * <p>
     * <b>Scenario:</b> Add two books, call {@code nextBook()} to move to index {@code 0},
     * then verify {@code getCurrentBook()} returns the first book.
     * </p>
     */
    @Test
    public void testGetCurrentBook() {
        Library lib = new Library("Nav Library", 3);
        lib.addBook(makeBook("First Book", "ISBN-F"));
        lib.addBook(makeBook("Second Book", "ISBN-S"));

        lib.nextBook(); // moves currentBookIndex to 0

        Book current = lib.getCurrentBook();
        assertNotNull("getCurrentBook() must not return null when a book is selected", current);
        assertEquals("Current book must be 'First Book'", "First Book", current.title);
    }

    /**
     * Verifies that {@link Library#nextBook()} advances the index and returns the
     * correct book on each call.
     * <p>
     * <b>Scenario:</b> Start at index {@code -1}; two consecutive calls must return
     * the first and second book.
     * </p>
     */
    @Test
    public void testNextBook() {
        Library lib = new Library("Nav Library", 3);
        lib.addBook(makeBook("Alpha", "ISBN-1"));
        lib.addBook(makeBook("Beta", "ISBN-2"));

        Book first = lib.nextBook();  // index -1 -> 0
        Book second = lib.nextBook(); // index  0 -> 1

        assertNotNull("First nextBook() call must not return null", first);
        assertNotNull("Second nextBook() call must not return null", second);
        assertEquals("First nextBook() must return 'Alpha'", "Alpha", first.title);
        assertEquals("Second nextBook() must return 'Beta'", "Beta", second.title);
    }

    /**
     * Verifies that {@link Library#nextBook()} returns {@code null} when called
     * beyond the last book.
     * <p>
     * <b>Scenario:</b> Library has {@code 1} book. After moving to it with
     * {@code nextBook()}, a subsequent call must return {@code null}.
     * </p>
     */
    @Test
    public void testNextBookPastEnd() {
        Library lib = new Library("One-Book Library", 2);
        lib.addBook(makeBook("Only Book", "ISBN-O"));

        lib.nextBook(); // index 0 – the only book
        Book result = lib.nextBook(); // beyond last

        assertNull("nextBook() must return null when no more books exist", result);
    }

    /**
     * Verifies that {@link Library#previousBook()} moves the index backward and
     * returns the correct book.
     * <p>
     * <b>Scenario:</b> Navigate to the second book, then call {@code previousBook()}
     * to return to the first.
     * </p>
     */
    @Test
    public void testPreviousBook() {
        Library lib = new Library("Nav Library", 3);
        lib.addBook(makeBook("Alpha", "ISBN-1"));
        lib.addBook(makeBook("Beta", "ISBN-2"));

        lib.nextBook(); // 0
        lib.nextBook(); // 1

        Book prev = lib.previousBook(); // back to 0

        assertNotNull("previousBook() must not return null when a previous book exists", prev);
        assertEquals("previousBook() must return 'Alpha'", "Alpha", prev.title);
    }

    /**
     * Verifies that {@link Library#previousBook()} returns {@code null} when already
     * positioned at the first book.
     * <p>
     * <b>Scenario:</b> Navigate to the first book with {@code nextBook()}, then call
     * {@code previousBook()} – no previous book exists.
     * </p>
     */
    @Test
    public void testPreviousBookBeforeStart() {
        Library lib = new Library("Nav Library", 2);
        lib.addBook(makeBook("Alpha", "ISBN-1"));

        lib.nextBook(); // index 0 – first (and only) book
        Book result = lib.previousBook(); // nothing before index 0

        assertNull("previousBook() must return null when already at the first book", result);
    }

    /**
     * Verifies that {@link Library#toString()} includes the library {@code name}.
     * <p>
     * <b>Requirement:</b> The returned {@link String} must contain the library name.
     * Individual book details must <em>not</em> appear.
     * </p>
     */
    @Test
    public void testToString() {
        Library lib = new Library("Grand Library", 5);
        lib.addBook(makeBook("Book One", "ISBN-1"));
        lib.addBook(makeBook("Book Two", "ISBN-2"));

        String str = lib.toString();

        assertNotNull("toString() must not return null", str);
        assertTrue("toString() must contain the library name", str.contains("Grand Library"));
    }
}