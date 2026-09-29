import org.junit.Test;
import static org.junit.Assert.*;

/**
 * {@link Book} test suite.
 * <p>
 * This class performs unit testing on the {@link Book} class to verify the
 * correctness of page management operations, navigation, concatenation, and
 * identity checks.
 * </p>
 * <p><b>Covered scenarios:</b></p>
 * <ul>
 * <li>Constructor – all fields initialized correctly; {@code currentPage} defaults to {@code 0}.</li>
 * <li>{@link Book#addPage(int, String)} – inserts a page at the given position and shifts subsequent pages.</li>
 * <li>{@link Book#replacePage(int, String)} – overwrites a specific page without altering others.</li>
 * <li>{@link Book#readPage()} – returns the current page content and advances the cursor.</li>
 * <li>{@link Book#goToFirstPage()} – resets the cursor to {@code 0}.</li>
 * <li>{@link Book#removePage(int)} – removes a page and shifts the remaining ones backward.</li>
 * <li>{@link Book#concatenateWith(Book)} – merges two books with the same author.</li>
 * <li>{@link Book#concatenateWith(Book)} – returns {@code null} for different authors.</li>
 * <li>{@link Book#toString()} – contains title, author, numPages, publication year, and ISBN.</li>
 * <li>{@link Book#equals(Object)} – {@code true} when ISBNs match; {@code false} otherwise.</li>
 * </ul>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Book
 */
public class Tests {

    /**
     * Creates a simple {@link Book} pre-loaded with three pages for reuse across tests.
     * <p>
     * <b>Pages (0-indexed):</b>
     * </p>
     * <ul>
     * <li>{@code 0} – {@code "Page one content"}</li>
     * <li>{@code 1} – {@code "Page two content"}</li>
     * <li>{@code 2} – {@code "Page three content"}</li>
     * </ul>
     *
     * @return a ready-to-use {@link Book} instance.
     */
    private Book createSampleBook() {
        String[] pages = {"Page one content", "Page two content", "Page three content"};
        return new Book("Sample Title", "Jane Doe", 2020, "ISBN-001", pages);
    }

    /**
     * Verifies that the constructor stores all fields correctly and that
     * {@code currentPage} is initialized to {@code 0}.
     */
    @Test
    public void testConstructor() {
        Book b = createSampleBook();

        assertEquals("Title must match", "Sample Title", b.title);
        assertEquals("Author must match", "Jane Doe", b.author);
        assertEquals("numPages must match", 3, b.numPages);
        assertEquals("publicationYear must match", 2020, b.publicationYear);
        assertEquals("ISBN must match", "ISBN-001", b.ISBN);
        assertNotNull("pages array must not be null", b.pages);
        assertEquals("currentPage must default to 0", 0, b.currentPage);
    }

    /**
     * Verifies that {@link Book#addPage(int, String)} inserts a new page at the
     * specified position and shifts all subsequent pages forward by one.
     * <p>
     * <b>Scenario:</b> Insert {@code "New page"} at index {@code 1} in a 3-page book.
     * Expected result: {@code ["Page one content", "New page", "Page two content", "Page three content"]}.
     * </p>
     */
    @Test
    public void testAddPage() {
        Book b = createSampleBook();

        b.addPage(1, "New page");

        assertEquals("Total pages must increase by 1", 4, b.pages.length);
        assertEquals("New page must be at index 1", "New page", b.pages[1]);
        assertEquals("Previous index-1 page shifts to 2", "Page two content", b.pages[2]);
        assertEquals("Previous index-2 page shifts to 3", "Page three content", b.pages[3]);
        assertEquals("Index-0 page must be unchanged", "Page one content", b.pages[0]);
    }

    /**
     * Verifies that {@link Book#replacePage(int, String)} overwrites the content
     * of the page at the specified index without affecting any other page.
     * <p>
     * <b>Scenario:</b> Replace the content at index {@code 1} ({@code "Page two content"})
     * with {@code "Replaced content"}.
     * Pages at indices {@code 0} and {@code 2} must remain unchanged.
     * </p>
     */
    @Test
    public void testReplacePage() {
        Book b = createSampleBook();

        b.replacePage(1, "Replaced content");

        assertEquals("Index 1 must have the new content", "Replaced content", b.pages[1]);
        assertEquals("Index 0 must be unchanged", "Page one content", b.pages[0]);
        assertEquals("Index 2 must be unchanged", "Page three content", b.pages[2]);
    }

    /**
     * Verifies that {@link Book#readPage()} returns the content of the current
     * page and advances {@code currentPage} by one after each call.
     * <p>
     * <b>Scenario:</b> Starting at page {@code 0}, two consecutive calls must return
     * {@code "Page one content"} and {@code "Page two content"} respectively, leaving
     * {@code currentPage} at {@code 2}.
     * </p>
     */
    @Test
    public void testReadPage() {
        Book b = createSampleBook();

        String first = b.readPage();
        String second = b.readPage();

        assertEquals("First readPage() call must return page 0 content", "Page one content", first);
        assertEquals("Second readPage() call must return page 1 content", "Page two content", second);
        assertEquals("currentPage must be 2 after two reads", 2, b.currentPage);
    }

    /**
     * Verifies that {@link Book#goToFirstPage()} resets {@code currentPage} to {@code 0}
     * regardless of the current position.
     * <p>
     * <b>Scenario:</b> Advance two pages by reading, then call {@code goToFirstPage()}.
     * {@code currentPage} must be {@code 0} afterwards.
     * </p>
     */
    @Test
    public void testGoToFirstPage() {
        Book b = createSampleBook();
        b.readPage(); // page 0 -> advance to 1
        b.readPage(); // page 1 -> advance to 2

        assertEquals("currentPage should be 2 before reset", 2, b.currentPage);

        b.goToFirstPage();

        assertEquals("goToFirstPage() must reset currentPage to 0", 0, b.currentPage);
    }

    /**
     * Verifies that {@link Book#removePage(int)} removes the page at the
     * given index and shifts all subsequent pages backward by one.
     * <p>
     * <b>Scenario:</b> Remove the page at index {@code 1} ({@code "Page two content"})
     * from a 3-page book.
     * Expected result: {@code ["Page one content", "Page three content"]}.
     * </p>
     */
    @Test
    public void testRemovePage() {
        Book b = createSampleBook();

        b.removePage(1);

        assertEquals("Array length must decrease by 1", 2, b.pages.length);
        assertEquals("Index 0 must be unchanged", "Page one content", b.pages[0]);
        assertEquals("Index 1 must be the former index-2 page", "Page three content", b.pages[1]);
    }

    /**
     * Verifies that {@link Book#concatenateWith(Book)} merges two books that share
     * the same author.
     * <p>
     * <b>Scenario:</b> Book A has pages {@code ["A1", "A2"]}; Book B has pages
     * {@code ["B1", "B2"]}; both by {@code "Jane Doe"}.
     * The resulting book must contain 4 pages in A–B order.
     * </p>
     */
    @Test
    public void testConcatenateWithSameAuthor() {
        String[] pagesA = {"A1", "A2"};
        String[] pagesB = {"B1", "B2"};

        Book bookA = new Book("Title A", "Jane Doe", 2019, "ISBN-A", pagesA);
        Book bookB = new Book("Title B", "Jane Doe", 2021, "ISBN-B", pagesB);

        Book combined = bookA.concatenateWith(bookB);

        assertNotNull("Result must not be null when authors match", combined);
        assertEquals("Combined book must have 4 pages", 4, combined.pages.length);
        assertEquals("First two pages come from bookA – index 0", "A1", combined.pages[0]);
        assertEquals("First two pages come from bookA – index 1", "A2", combined.pages[1]);
        assertEquals("Last two pages come from bookB – index 2", "B1", combined.pages[2]);
        assertEquals("Last two pages come from bookB – index 3", "B2", combined.pages[3]);
    }

    /**
     * Verifies that {@link Book#concatenateWith(Book)} returns {@code null} when the
     * two books have different authors.
     * <p>
     * <b>Requirement:</b> Concatenation is only allowed for books by the same author.
     * </p>
     */
    @Test
    public void testConcatenateWithDifferentAuthors() {
        String[] pagesA = {"A1"};
        String[] pagesB = {"B1"};

        Book bookA = new Book("Title A", "Jane Doe", 2019, "ISBN-A", pagesA);
        Book bookB = new Book("Title B", "John Smith", 2021, "ISBN-B", pagesB);

        Book combined = bookA.concatenateWith(bookB);

        assertNull("Result must be null when authors differ", combined);
    }

    /**
     * Verifies that {@link Book#toString()} contains all key metadata fields.
     * <p>
     * <b>Requirement:</b> The returned {@link String} must include the {@code title},
     * {@code author}, {@code numPages}, {@code publicationYear}, and {@code ISBN}.
     * The content of individual pages must <em>not</em> be part of the output.
     * </p>
     */
    @Test
    public void testToString() {
        Book b = createSampleBook();
        String str = b.toString();

        assertNotNull("toString() must not return null", str);
        assertTrue("Must contain title", str.contains("Sample Title"));
        assertTrue("Must contain author", str.contains("Jane Doe"));
        assertTrue("Must contain numPages", str.contains("3"));
    }

    /**
     * Verifies that {@link Book#equals(Object)} uses the {@code ISBN} as the sole
     * criterion for equality.
     * <p>
     * Two books with the same {@code ISBN} but different titles must be considered equal;
     * two books with different ISBNs must not.
     * </p>
     */
    @Test
    public void testEquals() {
        String[] p1 = {"Content"};
        String[] p2 = {"Other"};

        Book b1 = new Book("Different Title 1", "Author X", 2020, "SAME-ISBN", p1);
        Book b2 = new Book("Different Title 2", "Author Y", 2021, "SAME-ISBN", p2);
        Book b3 = new Book("Another Title", "Author Z", 2022, "DIFF-ISBN", p2);

        assertTrue("Books with same ISBN must be equal", b1.equals(b2));
        assertFalse("Books with different ISBNs must not be equal", b1.equals(b3));
    }
}