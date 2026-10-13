import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link Book} class: the constructor, the methods that add, replace and remove
 * pages, reading, joining two books, the written form and equality by ISBN.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Book
 */
public class Tests {

    /**
     * Creates a book with three pages: {@code "One"}, {@code "Two"} and {@code "Three"}.
     *
     * @return a new book with three pages, open at the first one.
     */
    private static Book createBook() {
        String[] pages = {"One", "Two", "Three"};
        return new Book("Sample Title", "Jane Doe", 2020, "ISBN-001", pages);
    }

    /**
     * Checks that every page of a book holds the expected text and that the number of pages
     * matches the length of the array.
     *
     * @param expected the text each page should hold, in order.
     * @param book the book to check.
     */
    private static void assertPages(String[] expected, Book book) {
        assertEquals("The book should have " + expected.length + " pages", expected.length, book.pages.length);
        assertEquals("numPages should match the length of the array", book.pages.length, book.numPages);
        for (int i = 0; i < expected.length; i++) {
            assertEquals("Page " + i + " should be " + expected[i], expected[i], book.pages[i]);
        }
    }

    /**
     * Checks that the constructor stores every attribute, counts the pages and opens the
     * book at page 0.
     */
    @Test
    public void testConstructor() {
        Book b = createBook();

        assertEquals("The title should be Sample Title", "Sample Title", b.title);
        assertEquals("The author should be Jane Doe", "Jane Doe", b.author);
        assertEquals("The publication year should be 2020", 2020, b.publicationYear);
        assertEquals("The ISBN should be ISBN-001", "ISBN-001", b.isbn);
        assertPages(new String[]{"One", "Two", "Three"}, b);
        assertEquals("A new book should be open at page 0", 0, b.currentPage);
    }

    /**
     * Checks {@link Book#addPage} in the middle: the pages after it move forward.
     */
    @Test
    public void testAddPageMiddle() {
        Book b = createBook();
        b.addPage(1, "New");

        assertPages(new String[]{"One", "New", "Two", "Three"}, b);
    }

    /**
     * Checks {@link Book#addPage} at both ends: before the first page and after the last one.
     */
    @Test
    public void testAddPageEnds() {
        Book b = createBook();
        b.addPage(0, "First");
        b.addPage(4, "Last");

        assertPages(new String[]{"First", "One", "Two", "Three", "Last"}, b);
    }

    /**
     * Checks that {@link Book#addPage} at a position outside the book leaves it unchanged.
     */
    @Test
    public void testAddPageInvalidPosition() {
        Book b = createBook();
        b.addPage(-1, "Before");
        b.addPage(4, "Past");

        assertPages(new String[]{"One", "Two", "Three"}, b);
    }

    /**
     * Checks that {@link Book#replacePage} changes only the given page, and nothing at a
     * position outside the book.
     */
    @Test
    public void testReplacePage() {
        Book b = createBook();
        b.replacePage(1, "Replaced");
        b.replacePage(3, "Past");

        assertPages(new String[]{"One", "Replaced", "Three"}, b);
    }

    /**
     * Checks that {@link Book#readPage} returns the pages in order, turning one page on each
     * call, and {@code null} past the last one.
     */
    @Test
    public void testReadPage() {
        Book b = createBook();

        assertEquals("The first read should return page 0", "One", b.readPage());
        assertEquals("The second read should return page 1", "Two", b.readPage());
        assertEquals("After two reads the book should be open at page 2", 2, b.currentPage);
        assertEquals("The third read should return page 2", "Three", b.readPage());
        assertNull("Reading past the last page should return null", b.readPage());
    }

    /**
     * Checks that {@link Book#goToFirstPage} opens the book at page 0 again.
     */
    @Test
    public void testGoToFirstPage() {
        Book b = createBook();
        b.readPage();
        b.readPage();
        b.goToFirstPage();

        assertEquals("The book should be open at page 0", 0, b.currentPage);
        assertEquals("The next read should return page 0", "One", b.readPage());
    }

    /**
     * Checks {@link Book#removePage} at the middle, the start and the end, and at a position
     * outside the book, which leaves it unchanged.
     */
    @Test
    public void testRemovePage() {
        Book b = createBook();
        b.addPage(3, "Four");

        b.removePage(1);
        assertPages(new String[]{"One", "Three", "Four"}, b);
        b.removePage(0);
        assertPages(new String[]{"Three", "Four"}, b);
        b.removePage(1);
        assertPages(new String[]{"Three"}, b);
        b.removePage(1);
        assertPages(new String[]{"Three"}, b);
    }

    /**
     * Checks {@link Book#concatenateWith} with the same author: a new book with the pages of
     * both, those of the first book first, and both originals unchanged.
     */
    @Test
    public void testConcatenateWithSameAuthor() {
        String[] pagesA = {"A1", "A2"};
        String[] pagesB = {"B1", "B2"};
        Book bookA = new Book("Title A", "Jane Doe", 2019, "ISBN-A", pagesA);
        Book bookB = new Book("Title B", "Jane Doe", 2021, "ISBN-B", pagesB);

        Book both = bookA.concatenateWith(bookB);

        assertNotNull("Two books by the same author should be joined", both);
        assertNotSame("The result should be a new book", bookA, both);
        assertEquals("The author should be Jane Doe", "Jane Doe", both.author);
        assertPages(new String[]{"A1", "A2", "B1", "B2"}, both);
        assertPages(new String[]{"A1", "A2"}, bookA);
        assertPages(new String[]{"B1", "B2"}, bookB);
    }

    /**
     * Checks that {@link Book#concatenateWith} returns {@code null} for books by different
     * authors.
     */
    @Test
    public void testConcatenateWithDifferentAuthors() {
        String[] pagesB = {"B1"};
        Book bookB = new Book("Title B", "John Smith", 2021, "ISBN-B", pagesB);

        assertNull("Books by different authors should not be joined", createBook().concatenateWith(bookB));
    }

    /**
     * Checks that {@link Book#describe} includes the data of the book but not the text of
     * its pages.
     */
    @Test
    public void testDescribe() {
        String text = createBook().describe();

        assertTrue("It should include the title", text.contains("Sample Title"));
        assertTrue("It should include the author", text.contains("Jane Doe"));
        assertTrue("It should include the publication year", text.contains("2020"));
        assertTrue("It should include the number of pages", text.contains("3"));
        assertTrue("It should include the ISBN", text.contains("ISBN-001"));
        assertFalse("It should not include the text of the pages", text.contains("Three"));
    }

    /**
     * Checks that {@link Book#isEqualTo} compares only the ISBN: books with the same ISBN are
     * equal whatever their other data, and books with different ISBNs are not.
     */
    @Test
    public void testIsEqualTo() {
        String[] p1 = {"Content"};
        String[] p2 = {"Other"};
        Book b1 = new Book("Title 1", "Author X", 2020, "SAME-ISBN", p1);
        Book b2 = new Book("Title 2", "Author Y", 2021, "SAME-ISBN", p2);
        Book b3 = new Book("Title 1", "Author X", 2020, "OTHER-ISBN", p1);

        assertTrue("Books with the same ISBN should be equal", b1.isEqualTo(b2));
        assertFalse("Books with different ISBNs should not be equal", b1.isEqualTo(b3));
    }
}
