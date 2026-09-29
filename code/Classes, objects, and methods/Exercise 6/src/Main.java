/**
 * Main application class to demonstrate the {@link Library} class.
 * Creates a {@link Library}, adds {@link Book} instances, and exercises all navigation operations.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Library
 * @see Book
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Create a library and verify the constructor
        Library library = new Library("City Library", 2);
        System.out.println("--- Library constructor ---");
        System.out.println("name (expected ity Library): " + library.name);
        System.out.println("books.length (expected 2):            " + library.books.length);
        System.out.println("currentBookIndex (expected -1):           " + library.currentBookIndex);
        System.out.println();

        // 2. Create several books
        Book book1 = new Book("The Hobbit", "Tolkien", 310, "ISBN-001", new String[]{"Page 1"});
        Book book2 = new Book("1984", "Orwell", 328, "ISBN-002", new String[]{"Page 1"});
        Book book3 = new Book("Brave New World", "Huxley", 1932, "ISBN-003", new String[]{"Page 1"});
        Book book4 = new Book("Fahrenheit 451", "Bradbury", 1953, "ISBN-004", new String[]{"Page 1"});

        // 3. Add books - the third insertion triggers array doubling (initial capacity is 2)
        library.addBook(book1);
        library.addBook(book2);
        System.out.println("--- After adding 2 books (array full) ---");
        System.out.println("books.length (expected 2): " + library.books.length);
        System.out.println();

        library.addBook(book3); // triggers doubling
        System.out.println("--- After adding 3rd book (doubling triggered) ---");
        System.out.println("books.length (expected >= 4): " + library.books.length);
        System.out.println("books[2].title (expected Brave New World): " + library.books[2].title);
        System.out.println();

        library.addBook(book4);
        System.out.println("--- After adding 4th book ---");
        System.out.println("books[3].title (expected Fahrenheit 451): " + library.books[3].title);
        System.out.println();

        // 4. toString()
        System.out.println("--- toString() ---");
        System.out.println(library);
        System.out.println();

        // 5. nextBook() - navigate forward
        System.out.println("--- nextBook() forward navigation ---");
        Book next1 = library.nextBook(); // -1 -> 0
        Book next2 = library.nextBook(); //  0 -> 1
        Book next3 = library.nextBook(); //  1 -> 2
        System.out.println("nextBook() #1 (expected The Hobbit):      " + next1.title);
        System.out.println("nextBook() #2 (expected 1984):            " + next2.title);
        System.out.println("nextBook() #3 (expected Brave New World): " + next3.title);
        System.out.println();

        // 6. getCurrentBook()
        System.out.println("--- getCurrentBook() ---");
        Book current = library.getCurrentBook();
        System.out.println("getCurrentBook() (expected Brave New World): " + current.title);
        System.out.println();

        // 7. previousBook() - navigate backward
        System.out.println("--- previousBook() backward navigation ---");
        Book prev1 = library.previousBook(); // 2 -> 1
        Book prev2 = library.previousBook(); // 1 -> 0
        System.out.println("previousBook() #1 (expected 1984):       " + prev1.title);
        System.out.println("previousBook() #2 (expected The Hobbit): " + prev2.title);
        System.out.println();

        // 8. previousBook() before the first book (expects null)
        Book beforeFirst = library.previousBook();
        System.out.println("--- previousBook() before first book ---");
        System.out.println("previousBook() before start (expected null): " + beforeFirst);
        System.out.println();

        // 9. nextBook() past the last book (expects null)
        library.nextBook(); // 0
        library.nextBook(); // 1
        library.nextBook(); // 2
        library.nextBook(); // 3
        Book pastEnd = library.nextBook();
        System.out.println("--- nextBook() past last book ---");
        System.out.println("nextBook() past end (expected null): " + pastEnd);
        System.out.println();
    }
}
