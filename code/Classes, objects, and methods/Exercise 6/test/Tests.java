import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link Library} class: the constructor, adding books with an array that doubles
 * when full, going through the books in both directions and the description.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Library
 * @see Book
 */
public class Tests {

    /**
     * Creates a book with a single page, by a generic author.
     *
     * @param title the title of the book.
     * @return a new book with that title.
     */
    private static Book createBook(String title) {
        String[] pages = {"Page"};
        return new Book(title, "Generic Author", 2020, "ISBN-" + title, pages);
    }

    /**
     * Creates a library with room for the given number of books and adds the given books.
     *
     * @param capacity the number of books the library has room for at first.
     * @param titles the titles of the books to add, in order.
     * @return the library, with its books added.
     */
    private static Library createLibrary(int capacity, String[] titles) {
        Library library = new Library("Test Library", capacity);
        for (String title : titles) {
            library.addBook(createBook(title));
        }
        return library;
    }

    /**
     * Checks that the constructor stores the name, creates an empty array of the given
     * length and selects no book.
     */
    @Test
    public void testConstructor() {
        Library library = new Library("City Library", 3);

        assertEquals("The name should be City Library", "City Library", library.name);
        assertEquals("The array should have room for 3 books", 3, library.books.length);
        for (Book book : library.books) {
            assertNull("A new library should have no books", book);
        }
        assertEquals("No book should be selected", -1, library.currentBookIndex);
        assertNull("There should be no current book", library.getCurrentBook());
    }

    /**
     * Checks that {@link Library#addBook} fills the array in order, from the first position,
     * without changing its length while there is room.
     */
    @Test
    public void testAddBook() {
        String[] titles = {"One", "Two"};
        Library library = createLibrary(3, titles);

        assertEquals("The array should still have room for 3", 3, library.books.length);
        assertEquals("The first book should be One", "One", library.books[0].title);
        assertEquals("The second book should be Two", "Two", library.books[1].title);
        assertNull("The third position should be empty", library.books[2]);
    }

    /**
     * Checks that {@link Library#addBook} with a full array doubles its length and keeps the
     * books already added in their positions.
     */
    @Test
    public void testAddBookDoubles() {
        String[] titles = {"A", "B", "C"};
        Library library = createLibrary(2, titles);

        assertEquals("The array should double to 4", 4, library.books.length);
        assertEquals("The first book should be A", "A", library.books[0].title);
        assertEquals("The second book should be B", "B", library.books[1].title);
        assertEquals("The third book should be C", "C", library.books[2].title);
        assertNull("The fourth position should be empty", library.books[3]);

        library.addBook(createBook("D"));
        library.addBook(createBook("E"));
        assertEquals("The array should double again to 8", 8, library.books.length);
        assertEquals("The fifth book should be E", "E", library.books[4].title);
    }

    /**
     * Checks that {@link Library#nextBook} selects and returns the books in order, and
     * returns {@code null} past the last one without changing the selection.
     */
    @Test
    public void testNextBook() {
        String[] titles = {"Alpha", "Beta"};
        Library library = createLibrary(3, titles);

        assertEquals("The first next book should be Alpha", "Alpha", library.nextBook().title);
        assertEquals("The current book should be Alpha", "Alpha", library.getCurrentBook().title);
        assertEquals("The second next book should be Beta", "Beta", library.nextBook().title);
        assertNull("There should be no book after Beta", library.nextBook());
        assertEquals("Beta should stay selected", "Beta", library.getCurrentBook().title);
        assertEquals("currentBookIndex should stay at 1", 1, library.currentBookIndex);
    }

    /**
     * Checks that {@link Library#previousBook} selects and returns the books backward, and
     * returns {@code null} before the first one without changing the selection.
     */
    @Test
    public void testPreviousBook() {
        String[] titles = {"Alpha", "Beta", "Gamma"};
        Library library = createLibrary(3, titles);
        library.nextBook();
        library.nextBook();
        library.nextBook();

        assertEquals("The first previous book should be Beta", "Beta", library.previousBook().title);
        assertEquals("The second previous book should be Alpha", "Alpha", library.previousBook().title);
        assertNull("There should be no book before Alpha", library.previousBook());
        assertEquals("Alpha should stay selected", "Alpha", library.getCurrentBook().title);
    }

    /**
     * Checks that both directions return {@code null} in a library without books, and that
     * {@link Library#previousBook} does too when no book is selected yet.
     */
    @Test
    public void testNavigationEdges() {
        Library empty = new Library("Empty", 2);
        assertNull("An empty library should have no next book", empty.nextBook());
        assertNull("An empty library should have no previous book", empty.previousBook());
        assertEquals("No book should be selected", -1, empty.currentBookIndex);

        String[] titles = {"Alpha"};
        Library library = createLibrary(2, titles);
        assertNull("With no book selected, there should be no previous book", library.previousBook());
    }

    /**
     * Checks that {@link Library#describe} includes the name of the library but not the data
     * of its books.
     */
    @Test
    public void testDescribe() {
        String[] titles = {"Book One", "Book Two"};
        Library library = createLibrary(5, titles);

        String text = library.describe();

        assertTrue("It should include the name", text.contains("Test Library"));
        assertTrue("It should include the number of books", text.contains("2"));
        assertFalse("It should not include the titles of the books", text.contains("Book One"));
    }
}
