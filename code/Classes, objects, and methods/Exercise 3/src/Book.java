/**
 * Represents a book: its data, the text of each page and the page it is open at.
 * <p>
 * The pages are kept in an array with one position per page. An array cannot change its
 * size, so adding or removing a page creates a new array and copies the pages into it.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
class Book {

    /**
     * The title of the book.
     */
    String title;

    /**
     * The author of the book.
     */
    String author;

    /**
     * The number of pages, which is always the length of {@link #pages}.
     */
    int numPages;

    /**
     * The year the book was published.
     */
    int publicationYear;

    /**
     * The International Standard Book Number, which identifies the book and never changes.
     */
    final String isbn;

    /**
     * The text of each page, the first one at position 0.
     */
    String[] pages;

    /**
     * The position of the page the book is open at, the first one when it is created.
     */
    int currentPage;

    /**
     * Creates a book open at its first page.
     *
     * @param title the title of the book.
     * @param author the author of the book.
     * @param publicationYear the year the book was published.
     * @param isbn the International Standard Book Number.
     * @param pages the text of each page.
     */
    Book(String title, String author, int publicationYear, String isbn, String[] pages) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.pages = pages;
        this.numPages = pages.length;
        this.currentPage = 0;
    }

    /**
     * Adds a page at the given position; that page and the following ones move forward one
     * position.
     * <p>
     * The position can go from 0, before the first page, to {@link #numPages}, after the
     * last one. For any other position the book does not change.
     *
     * @param position the position of the new page.
     * @param text the text of the new page.
     */
    void addPage(int position, String text) {
        if (position < 0 || position > this.numPages) {
            return;
        }
        String[] newPages = new String[this.numPages + 1];
        for (int i = 0; i < position; i++) {
            newPages[i] = this.pages[i];
        }
        newPages[position] = text;
        for (int i = position; i < this.numPages; i++) {
            newPages[i + 1] = this.pages[i];
        }
        this.pages = newPages;
        this.numPages = newPages.length;
    }

    /**
     * Replaces the text of the page at the given position. If there is no page at that
     * position, the book does not change.
     *
     * @param position the position of the page.
     * @param text the new text of the page.
     */
    void replacePage(int position, String text) {
        if (position >= 0 && position < this.numPages) {
            this.pages[position] = text;
        }
    }

    /**
     * Returns the text of the page the book is open at and turns to the next page.
     *
     * @return the text of the current page, or {@code null} if the book is past its last page.
     */
    String readPage() {
        if (this.currentPage >= this.numPages) {
            return null;
        }
        String text = this.pages[this.currentPage];
        this.currentPage++;
        return text;
    }

    /**
     * Opens the book at its first page again.
     */
    void goToFirstPage() {
        this.currentPage = 0;
    }

    /**
     * Removes the page at the given position; the following pages move back one position.
     * If there is no page at that position, the book does not change.
     *
     * @param position the position of the page to remove.
     */
    void removePage(int position) {
        if (position < 0 || position >= this.numPages) {
            return;
        }
        String[] newPages = new String[this.numPages - 1];
        for (int i = 0; i < position; i++) {
            newPages[i] = this.pages[i];
        }
        for (int i = position + 1; i < this.numPages; i++) {
            newPages[i - 1] = this.pages[i];
        }
        this.pages = newPages;
        this.numPages = newPages.length;
    }

    /**
     * Joins this book and another one by the same author into a new book: first the pages of
     * this book, then those of the other.
     * <p>
     * The new book is titled {@code "First & Second"}, takes the later of both publication
     * years and has both ISBNs, joined by a {@code /}, as its own.
     *
     * @param other the book whose pages go after those of this one.
     * @return a new book with the pages of both, or {@code null} if the authors differ.
     */
    Book concatenateWith(Book other) {
        if (!this.author.equals(other.author)) {
            return null;
        }
        String[] newPages = new String[this.numPages + other.numPages];
        for (int i = 0; i < this.numPages; i++) {
            newPages[i] = this.pages[i];
        }
        for (int i = 0; i < other.numPages; i++) {
            newPages[this.numPages + i] = other.pages[i];
        }
        return new Book(this.title + " & " + other.title, this.author,
                Math.max(this.publicationYear, other.publicationYear), this.isbn + "/" + other.isbn, newPages);
    }

    /**
     * Returns the data of the book, without the text of its pages, such as
     * {@code "Dune, by Frank Herbert (1965), 412 pages, ISBN 978-0441013593"}.
     *
     * @return the title, author, publication year, number of pages and ISBN.
     */
    String describe() {
        return this.title + ", by " + this.author + " (" + this.publicationYear + "), " + this.numPages
                + " pages, ISBN " + this.isbn;
    }

    /**
     * Checks whether this book and another one are the same book, which happens when they
     * have the same ISBN, whatever their other data.
     *
     * @param other the book to compare with.
     * @return {@code true} if both books have the same ISBN, {@code false} otherwise.
     */
    boolean isEqualTo(Book other) {
        return this.isbn.equals(other.isbn);
    }
}
