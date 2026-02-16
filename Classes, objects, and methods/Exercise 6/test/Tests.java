import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class performs unit testing on the {@link Library} class to verify 
 * the correctness of dynamic collection management, automated array resizing,
 * and bidirectional navigation through {@link Book} objects.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Library
 * @see Book
 */
public class Tests {

    /**
     * Verifies the {@link Library} constructor and its initial state.
     * <p>
     * <b>Requirement:</b> The library must start without books and the 
     * {@link Library#currentBookIndex} must be initialized to {@code -1}.
     * </p>
     */
    @Test
    public void testConstructor() {
        String libName = "Central Library";
        int capacity = 5;
        Library lib = new Library(libName, capacity);

        assertEquals("The library name should be correctly assigned",
                libName, lib.name); //
        assertEquals("The initial book index must be -1 (no selection)",
                -1, lib.currentBookIndex); //
        assertEquals("The internal array should match the initial capacity",
                capacity, lib.books.length); // [cite: 1]
    }

    /**
     * Verifies the <b>automatic resizing</b> logic of the internal array.
     * <p>
     * <b>Requirement:</b> If the array is full, its size must be doubled
     * before adding a new book.
     * </p>
     * <p><b>Logic:</b> Capacity 2 -> Add 2 books -> Add 3rd book -> Capacity becomes 4.</p>
     */
    @Test
    public void testAddBookWithResize() {
        // Create a library with a very small capacity to trigger resize logic
        Library lib = new Library("Resize Test", 2);
        Book b1 = new Book("Title 1", "Author", 2024, "ISBN-1", new String[]{"P1"});
        Book b2 = new Book("Title 2", "Author", 2024, "ISBN-2", new String[]{"P1"});
        Book b3 = new Book("Title 3", "Author", 2024, "ISBN-3", new String[]{"P1"});

        lib.addBook(b1);
        lib.addBook(b2);
        assertEquals("Array should be at its limit", 2, lib.books.length);

        // This addition must trigger the double-size logic
        lib.addBook(b3);

        assertEquals("The array size should have doubled to 4", 4, lib.books.length);
        assertEquals("The third book should be stored at index 2", b3, lib.books[2]);
    }

    /**
     * Verifies the <b>navigation flow</b> and boundary conditions.
     * <p>
     * <b>Requirement:</b> {@link Library#nextBook} and {@link Library#previousBook}
     * must move the index and return {@code null} when out of bounds.
     * </p>
     */
    @Test
    public void testNavigationLogic() {
        Library lib = new Library("Nav Test", 10);
        Book b1 = new Book("B1", "A", 2000, "I1", new String[]{"P"});
        Book b2 = new Book("B2", "A", 2000, "I2", new String[]{"P"});

        lib.addBook(b1); // Index 0
        lib.addBook(b2); // Index 1

        // 1. Test next: -1 -> 0
        Book first = lib.nextBook();
        assertEquals("Next should select the first book (index 0)", b1, first);
        assertEquals("Current index should be 0", 0, lib.currentBookIndex);

        // 2. Test next: 0 -> 1
        Book second = lib.nextBook();
        assertEquals("Next should select the second book (index 1)", b2, second);

        // 3. Test next: Boundary (end of collection)
        Book end = lib.nextBook();
        assertNull("Should return null at the end of the collection", end); //
        assertEquals("Index should remain at the last valid book", 1, lib.currentBookIndex);

        // 4. Test previous: 1 -> 0
        Book back = lib.previousBook();
        assertEquals("Previous should return to index 0", b1, back);

        // 5. Test previous: Boundary (start of collection)
        Book start = lib.previousBook();
        assertNull("Should return null when moving before the first book", start); //
    }

    /**
     * Verifies {@link Library#getCurrentBook} based on the index.
     */
    @Test
    public void testGetCurrentBook() {
        Library lib = new Library("Selection Test", 5);
        Book b1 = new Book("B1", "A", 2000, "I1", new String[]{"P"});
        lib.addBook(b1);

        // Initially -1, so getCurrentBook should be null
        assertNull("Initial selection should be null", lib.getCurrentBook());

        lib.nextBook(); // Move to 0
        assertEquals("Should return the book at current index", b1, lib.getCurrentBook()); // [cite: 1]
    }

    /**
     * Verifies the {@link Library#toString} implementation.
     * <p>
     * <b>Requirement:</b> Should only print library metadata, not individual books.
     * </p>
     */
    @Test
    public void testToStringMetadata() {
        Library lib = new Library("UCLM Library", 10);
        lib.addBook(new Book("B1", "A", 2024, "ISBN", new String[]{""}));

        String output = lib.toString(); // [cite: 1]

        assertTrue("String should contain the library name", output.contains("UCLM Library")); //
        assertTrue("String should show the total book count", output.contains("1 books")); // [cite: 1]
        assertFalse("String should NOT list internal ISBNs or book details", output.contains("ISBN")); //
    }
}