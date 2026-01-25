/**
 * Represents a generic book with metadata and dynamic page content.
 * Demonstrates manual array resizing, state management, and object comparison.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Book {

    /** The title of the book. */
    public String title;

    /** The author of the book. */
    public String author;

    /** Total number of pages in the book. */
    public int numPages;

    /** Year the book was published. */
    public int publicationYear;

    /** The International Standard Exercise 3 Number. */
    public String ISBN;

    /** Array containing the text content of each page. */
    public String[] pages;

    /** The index of the page currently being read. */
    public int currentPage = 0;

    /**
     * Constructs a new {@link Book} with the specified metadata.
     * Note: {@link Book#currentPage} is not an argument as it defaults to 0.
     *
     * @param title Title of the book.
     * @param author Author's name.
     * @param publicationYear Year of release.
     * @param ISBN Unique identifier code.
     * @param pages Initial array of page contents.
     */
    public Book(String title, String author, int publicationYear, String ISBN, String[] pages) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.ISBN = ISBN;
        this.pages = pages;
        this.numPages = pages.length;
    }

    /**
     * Adds a new page at the specified position.
     * This method resizes the internal array and shifts subsequent elements.
     *
     * @param position The index where the page should be inserted.
     * @param content The text content of the new page.
     */
    public void addPage(int position, String content) {
        if (position < 0 || position > this.pages.length) return;

        String[] newPages = new String[this.pages.length + 1];
        for (int i = 0, j = 0; i < newPages.length; i++) {
            if (i == position) {
                newPages[i] = content;
            } else {
                newPages[i] = this.pages[j++];
            }
        }
        this.pages = newPages;
        this.numPages = this.pages.length;
    }

    /**
     * Replaces the content of an existing page.
     *
     * @param position The index of the page to modify.
     * @param text The new text content.
     */
    public void replacePage(int position, String text) {
        if (position >= 0 && position < this.pages.length) {
            this.pages[position] = text;
        }
    }

    /**
     * Returns the current page content and advances the reader.
     *
     * @return The text of the current page, or an end-of-book message.
     */
    public String readPage() {
        if (this.currentPage < this.pages.length) {
            return this.pages[this.currentPage++];
        }
        return "[End of book]";
    }

    /**
     * Resets the reading progress to the beginning of the book.
     */
    public void goToFirstPage() {
        this.currentPage = 0;
    }

    /**
     * Removes the page at the specified position.
     * This method resizes the internal array and shifts subsequent elements back.
     *
     * @param position The index of the page to remove.
     */
    public void removePage(int position) {
        if (position < 0 || position >= this.pages.length) return;

        String[] newPages = new String[this.pages.length - 1];
        for (int i = 0, j = 0; i < this.pages.length; i++) {
            if (i != position) {
                newPages[j++] = this.pages[i];
            }
        }
        this.pages = newPages;
        this.numPages = this.pages.length;
    }

    /**
     * Creates a new {@link Book} by combining the pages of this book and another.
     * Only valid if both books share the same author.
     *
     * @param other The {@link Book} to combine with.
     * @return A new {@link Book} instance, or null if authors differ.
     */
    public Book concatenateWith(Book other) {
        if (!this.author.equals(other.author)) {
            return null;
        }

        String[] combinedPages = new String[this.pages.length + other.pages.length];
        System.arraycopy(this.pages, 0, combinedPages, 0, this.pages.length);
        System.arraycopy(other.pages, 0, combinedPages, this.pages.length, other.pages.length);

        return new Book(this.title + " & " + other.title, this.author,
                2026, this.ISBN + "-EXT", combinedPages);
    }

    /**
     * Returns a string with the book's metadata.
     *
     * @return Summary of title, author, and page count.
     */
    @Override
    public String toString() {
        return "'" + this.title + "' by " + this.author + " (" + this.numPages + " pages). ISBN: " + this.ISBN;
    }

    /**
     * Compares this book to another object based on the {@link Book#ISBN}.
     *
     * @param o The object to compare with.
     * @return {@code true} if both are books and share the same {@link Book#ISBN}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return this.ISBN.equals(book.ISBN);
    }

    /**
     * Entry point to verify all functional requirements of the exercise.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        // 1. Creation and addPage test
        String[] initialPages = {"Intro content"};
        Book b1 = new Book("Java basics", "J. Alfaro", 2024, "ISBN-001", initialPages);
        b1.addPage(1, "Variables and types");
        b1.addPage(2, "Classes and objects");

        // 2. replacePage and readPage test
        b1.replacePage(0, "Revised introduction");
        System.out.println("Reading B1 content:");
        System.out.println("Page 1: " + b1.readPage());
        System.out.println("Page 2: " + b1.readPage());

        // 3. removePage test
        b1.removePage(1); // Remove "Variables and Types"
        System.out.println("\nMetadata after removal: " + b1.toString());

        // 4. concatenateWith and equals test
        Book b2 = new Book("Advanced Java", "J. Alfaro", 2025, "ISBN-002", new String[]{"Generics"});
        Book combined = b1.concatenateWith(b2);
        if (combined != null) {
            System.out.println("Combined book: " + combined.toString());
        }

        Book b3 = new Book("Clone", "Other", 2024, "ISBN-001", new String[]{""});
        System.out.println("B1 equals B3 (same ISBN)? " + b1.equals(b3));
    }
}
