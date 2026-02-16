import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class performs unit testing on the {@link Book} class to verify
 * the correctness of dynamic page management (manual array resizing),
 * reading state navigation, and object comparison logic.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Book
 */
public class Tests {

    /**
     * Verifies the <b>constructor</b> initialization.
     * <p>
     * <b>Requirement:</b> "A constructor that takes all relevant values... default values should not be passed."
     * </p>
     * Checks that metadata is stored and {@link Book#currentPage} defaults to 0.
     */
    @Test
    public void testConstructor() {
        String[] pages = {"Page 1", "Page 2"};
        Book book = new Book("Title", "Author", 2024, "ISBN-123", pages);

        assertEquals("Title should be set", "Title", book.title);
        assertEquals("Author should be set", "Author", book.author);
        assertEquals("NumPages should match array length", 2, book.numPages);
        assertEquals("CurrentPage should default to 0", 0, book.currentPage);
        assertEquals("ISBN should be set", "ISBN-123", book.ISBN);
    }

    /**
     * Verifies {@link Book#addPage} logic: <b>Insertion at the beginning</b>.
     * <p>
     * <b>Requirement:</b> "Adds a new page... all subsequent pages must move forward."
     * </p>
     * <p><b>Logic:</b> [A, B] -> insert "New" at 0 -> [New, A, B]</p>
     */
    @Test
    public void testAddPageAtBeginning() {
        String[] pages = {"Old First", "Old Second"};
        Book book = new Book("T", "A", 2000, "I", pages);

        book.addPage(0, "New First");

        assertEquals("NumPages should increase by 1", 3, book.numPages);
        assertEquals("Index 0 should be the new page", "New First", book.pages[0]);
        assertEquals("Index 1 should be shifted original", "Old First", book.pages[1]);
    }

    /**
     * Verifies {@link Book#addPage} logic: <b>Insertion in the middle</b>.
     * <p>
     * <b>Logic:</b> [A, C] -> insert "B" at 1 -> [A, B, C]</p>
     */
    @Test
    public void testAddPageInMiddle() {
        String[] pages = {"Page A", "Page C"};
        Book book = new Book("T", "A", 2000, "I", pages);

        book.addPage(1, "Page B");

        assertEquals("NumPages should be 3", 3, book.numPages);
        assertEquals("Page A should remain at 0", "Page A", book.pages[0]);
        assertEquals("Page B should be at 1", "Page B", book.pages[1]);
        assertEquals("Page C should be shifted to 2", "Page C", book.pages[2]);
    }

    /**
     * Verifies {@link Book#addPage} logic: <b>Appending at the end</b>.
     * <p>
     * <b>Logic:</b> [A] -> insert "B" at 1 (length) -> [A, B]</p>
     */
    @Test
    public void testAddPageAtEnd() {
        String[] pages = {"Page A"};
        Book book = new Book("T", "A", 2000, "I", pages);

        // Appending at index == length is valid
        book.addPage(1, "Page B");

        assertEquals("NumPages should be 2", 2, book.numPages);
        assertEquals("Page B should be at the end", "Page B", book.pages[1]);
    }

    /**
     * Verifies {@link Book#removePage} logic.
     * <p>
     * <b>Requirement:</b> "Removes a page... moving all subsequent pages backward."
     * </p>
     * <p><b>Logic:</b> [A, B, C] -> remove 1 ("B") -> [A, C]</p>
     */
    @Test
    public void testRemovePage() {
        String[] pages = {"A", "B", "C"};
        Book book = new Book("T", "A", 2000, "I", pages);

        book.removePage(1);

        assertEquals("NumPages should decrease by 1", 2, book.numPages);
        assertEquals("Index 0 should remain 'A'", "A", book.pages[0]);
        assertEquals("Index 1 should now be 'C'", "C", book.pages[1]);
    }

    /**
     * Verifies {@link Book#replacePage}.
     * <p>
     * <b>Requirement:</b> "Replaces the content of a specified page with new text."
     * </p>
     */
    @Test
    public void testReplacePage() {
        String[] pages = {"Draft"};
        Book book = new Book("T", "A", 2000, "I", pages);

        book.replacePage(0, "Final Version");

        assertEquals("Content should be updated", "Final Version", book.pages[0]);
        assertEquals("Size should not change", 1, book.numPages);
    }

    /**
     * Verifies reading navigation: {@link Book#readPage} and {@link Book#goToFirstPage}.
     * <p>
     * <b>Requirement:</b> "Returns content... advances to next... resets current page."
     * </p>
     */
    @Test
    public void testReadingNavigation() {
        String[] pages = {"P1", "P2"};
        Book book = new Book("T", "A", 2000, "I", pages);

        // 1. Read first page
        assertEquals("Should return first page", "P1", book.readPage());
        assertEquals("Current page should advance to 1", 1, book.currentPage);

        // 2. Read second page
        assertEquals("Should return second page", "P2", book.readPage());
        assertEquals("Current page should advance to 2", 2, book.currentPage);

        // 3. Read beyond end
        assertEquals("Should indicate end of book", "[End of book]", book.readPage());

        // 4. Reset
        book.goToFirstPage();
        assertEquals("Current page should be 0", 0, book.currentPage);
        assertEquals("Should read first page again", "P1", book.readPage());
    }

    /**
     * Verifies {@link Book#concatenateWith} logic.
     * <p>
     * <b>Requirement:</b> "Returns a NEW book containing pages of both... only if they share the same author."
     * </p>
     */
    @Test
    public void testConcatenateWithSameAuthor() {
        Book b1 = new Book("Vol 1", "J.K.", 2000, "I1", new String[]{"Start"});
        Book b2 = new Book("Vol 2", "J.K.", 2001, "I2", new String[]{"End"});

        Book combined = b1.concatenateWith(b2);

        assertNotNull("Should return a new book for same author", combined);
        assertEquals("Combined pages count should be 2", 2, combined.numPages);
        assertEquals("First page should be from b1", "Start", combined.pages[0]);
        assertEquals("Second page should be from b2", "End", combined.pages[1]);
        assertEquals("Author should be preserved", "J.K.", combined.author);
    }

    /**
     * Verifies {@link Book#concatenateWith} failure case.
     */
    @Test
    public void testConcatenateWithDiffAuthor() {
        Book b1 = new Book("B1", "Author A", 2000, "I1", new String[]{"Content"});
        Book b2 = new Book("B2", "Author B", 2000, "I2", new String[]{"Content"});

        Book combined = b1.concatenateWith(b2);

        assertNull("Should return null if authors differ", combined);
    }

    /**
     * Verifies {@link Book#equals} logic.
     * <p>
     * <b>Requirement:</b> "Check if two books are equal based solely on their ISBN."
     * </p>
     */
    @Test
    public void testEquals() {
        Book b1 = new Book("Title 1", "A", 2000, "ISBN-SAME", new String[]{});
        Book b2 = new Book("Title 2", "B", 2024, "ISBN-SAME", new String[]{});
        Book b3 = new Book("Title 1", "A", 2000, "ISBN-DIFF", new String[]{});

        assertTrue("Books with same ISBN should be equal", b1.equals(b2));
        assertFalse("Books with diff ISBN should not be equal", b1.equals(b3));
    }
}
