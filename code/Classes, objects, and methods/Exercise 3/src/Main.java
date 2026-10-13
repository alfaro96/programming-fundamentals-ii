/**
 * Exercise 3: tries every method of the {@link Book} class.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Book
 */
class Main {

    /**
     * Prints every page of a book, one per line, with its position.
     *
     * @param book the book whose pages are printed.
     */
    static void printPages(Book book) {
        for (int i = 0; i < book.numPages; i++) {
            System.out.println("  " + i + ": " + book.pages[i]);
        }
    }

    /**
     * Creates a book and changes its pages, reads it, and then creates two more books to try
     * {@link Book#concatenateWith} and {@link Book#isEqualTo}.
     *
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        String[] pages = {"It was a dark night.", "The end."};
        Book book = new Book("The Night", "Jane Doe", 2020, "ISBN-001", pages);
        System.out.println(book.describe());

        // Pages added at the start, in the middle and at the end
        book.addPage(0, "Chapter 1");
        book.addPage(2, "It started to rain.");
        book.addPage(4, "Afterword");
        System.out.println("After adding three pages (should be 5):");
        printPages(book);

        book.replacePage(4, "Acknowledgments");
        System.out.println("After replacing page 4:");
        printPages(book);

        System.out.println("Reading: " + book.readPage());
        System.out.println("Reading: " + book.readPage());
        System.out.println("Open at page " + book.currentPage + " (should be 2)");
        book.goToFirstPage();
        System.out.println("Open at page " + book.currentPage + " (should be 0)");

        book.removePage(2);
        System.out.println("After removing page 2 (should be 4):");
        printPages(book);

        // Two books by the same author can be joined
        String[] sequelPages = {"Morning came."};
        Book sequel = new Book("The Day", "Jane Doe", 2022, "ISBN-002", sequelPages);
        Book both = book.concatenateWith(sequel);
        System.out.println(both.describe());
        printPages(both);

        String[] otherPages = {"Hello."};
        Book other = new Book("Other", "John Smith", 2021, "ISBN-003", otherPages);
        System.out.println("Different authors: " + book.concatenateWith(other) + " (should be null)");

        // Same ISBN, same book, even with another title
        Book sameIsbn = new Book("The Night (2nd ed.)", "Jane Doe", 2024, "ISBN-001", otherPages);
        System.out.println("book equals sameIsbn: " + book.isEqualTo(sameIsbn) + " (should be true)");
        System.out.println("book equals sequel: " + book.isEqualTo(sequel) + " (should be false)");
    }
}
