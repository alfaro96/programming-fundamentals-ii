/**
 * Exercise 6: tries every method of the {@link Library} class.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Library
 * @see Book
 */
class Main {

    /**
     * Creates a library with room for two books, adds four so that its array doubles, and
     * goes through it forward and backward, past both ends.
     *
     * @param args the command-line arguments (not used).
     */
    public static void main(String[] args) {
        Library library = new Library("City Library", 2);
        System.out.println(library.describe() + " (should have 0 books, room for 2)");

        String[] pages = {"Page 1", "Page 2"};
        library.addBook(new Book("The Hobbit", "J. R. R. Tolkien", 1937, "ISBN-001", pages));
        library.addBook(new Book("1984", "George Orwell", 1949, "ISBN-002", pages));
        System.out.println(library.describe() + " (should have 2 books, room for 2)");

        // The array is full: the third book doubles it
        library.addBook(new Book("Brave New World", "Aldous Huxley", 1932, "ISBN-003", pages));
        System.out.println(library.describe() + " (should have 3 books, room for 4)");
        library.addBook(new Book("Fahrenheit 451", "Ray Bradbury", 1953, "ISBN-004", pages));
        System.out.println(library.describe() + " (should have 4 books, room for 4)");

        System.out.println("Current book: " + library.getCurrentBook() + " (should be null)");

        // Forward, one past the last book
        for (int i = 0; i < 5; i++) {
            Book book = library.nextBook();
            if (book == null) {
                System.out.println("Next: none, the last one stays selected");
            } else {
                System.out.println("Next: " + book.title);
            }
        }
        System.out.println("Current book: " + library.getCurrentBook().title + " (should be Fahrenheit 451)");

        // Backward, one past the first book
        for (int i = 0; i < 4; i++) {
            Book book = library.previousBook();
            if (book == null) {
                System.out.println("Previous: none, the first one stays selected");
            } else {
                System.out.println("Previous: " + book.title);
            }
        }
        System.out.println("Current book: " + library.getCurrentBook().title + " (should be The Hobbit)");
    }
}
