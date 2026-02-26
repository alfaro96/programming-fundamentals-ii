/**
 * Main application class to demonstrate the {@link Book} class.
 * Creates {@link Book} instances and exercises all their operations.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Book
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Create a book and verify the constructor
        String[] pages = {"Page one content", "Page two content", "Page three content"};
        Book book = new Book("Sample Title", "Jane Doe", 2020, "ISBN-001", pages);
        System.out.println("--- Book constructor ---");
        System.out.println(book);
        System.out.println("currentPage (expected 0): " + book.currentPage);
        System.out.println();

        // 2. addPage() - insert a new page at index 1
        book.addPage(1, "New page");
        System.out.println("--- addPage(1, \"New page\") ---");
        System.out.println("pages.length (expected 4): " + book.pages.length);
        System.out.println("pages[0]: " + book.pages[0]);
        System.out.println("pages[1] (expected new page): " + book.pages[1]);
        System.out.println("pages[2] (expected page two content): " + book.pages[2]);
        System.out.println("pages[3] (expected page three content): " + book.pages[3]);
        System.out.println();

        // 3. replacePage() - overwrite index 1
        book.replacePage(1, "Replaced content");
        System.out.println("--- replacePage(1, \"Replaced content\") ---");
        System.out.println("pages[0] (expected page one content): " + book.pages[0]);
        System.out.println("pages[1] (expected replaced content): " + book.pages[1]);
        System.out.println();

        // 4. readPage() - iterate through two pages
        System.out.println("--- readPage() ---");
        System.out.println("currentPage before reads: " + book.currentPage);
        String read1 = book.readPage();
        String read2 = book.readPage();
        System.out.println("readPage() #1: " + read1);
        System.out.println("readPage() #2: " + read2);
        System.out.println("currentPage after 2 reads (expected 2): " + book.currentPage);
        System.out.println();

        // 5. goToFirstPage() - reset the cursor
        book.goToFirstPage();
        System.out.println("--- goToFirstPage() ---");
        System.out.println("currentPage after reset (expected 0): " + book.currentPage);
        System.out.println();

        // 6. removePage() - remove page at index 1
        book.removePage(1);
        System.out.println("--- removePage(1) ---");
        System.out.println("pages.length (expected 3): " + book.pages.length);
        System.out.println("pages[0]: " + book.pages[0]);
        System.out.println("pages[1]: " + book.pages[1]);
        System.out.println("pages[2]: " + book.pages[2]);
        System.out.println();

        // 7. concatenateWith() - same author
        String[] pagesA = {"A1", "A2"};
        String[] pagesB = {"B1", "B2"};
        Book bookA = new Book("Title A", "Jane Doe", 2019, "ISBN-A", pagesA);
        Book bookB = new Book("Title B", "Jane Doe", 2021, "ISBN-B", pagesB);
        Book combined = bookA.concatenateWith(bookB);
        System.out.println("--- concatenateWith() - same author ---");
        System.out.println("combined.pages.length (expected 4): " + combined.pages.length);
        System.out.println("combined.pages[0] (expected A1): " + combined.pages[0]);
        System.out.println("combined.pages[2] (expected B1): " + combined.pages[2]);
        System.out.println();

        // 8. concatenateWith() - different authors (expects null)
        Book bookC = new Book("Title C", "John Smith", 2021, "ISBN-C", pagesB);
        Book combinedNull = bookA.concatenateWith(bookC);
        System.out.println("--- concatenateWith() - different authors ---");
        System.out.println("result (expected null): " + combinedNull);
        System.out.println();

        // 9. toString()
        System.out.println("--- toString() ---");
        System.out.println(bookA);
        System.out.println();

        // 10. equals() - ISBN-based comparison
        String[] dummy = {"x"};
        Book sameISBN = new Book("Other Title", "Other Author", 2022, "ISBN-A", dummy);
        Book diffISBN = new Book("Other Title", "Other Author", 2022, "ISBN-Z", dummy);
        System.out.println("--- equals() ---");
        System.out.println("bookA.equals(sameISBN) (expected true):  " + bookA.equals(sameISBN));
        System.out.println("bookA.equals(diffISBN) (expected false): " + bookA.equals(diffISBN));
        System.out.println();
    }
}
