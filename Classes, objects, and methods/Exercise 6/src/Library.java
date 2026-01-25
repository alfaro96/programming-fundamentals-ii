/**
 * Represents a library that manages a collection of {@link Book} objects.
 * It handles dynamic array growth and provides navigation through the collection.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Library {

   /** The name of the library. */
   public String name;

   /** The array containing the books in the library. */
   public Book[] books;

   /** The index of the book currently being selected or read. */
   public int currentBookIndex = -1;

   /** Number of books currently stored in the library. */
   private int bookCount = 0;

   /**
    * Constructs a new {@link Library} with a name and initial capacity.
    *
    * @param name The name of the library.
    * @param initialCapacity The starting size of the books array.
    */
   public Library(String name, int initialCapacity) {
      this.name = name;
      this.books = new Book[initialCapacity];
   }

   /**
    * Adds a book to the library. If the internal array is full, its size is doubled.
    *
    * @param book The {@link Book} to add.
    */
   public void addBook(Book book) {
      if (this.bookCount == this.books.length) {
         // Resize logic: Double the array size
         Book[] largerArray = new Book[this.books.length * 2];
         for (int i = 0; i < this.books.length; i++) {
            largerArray[i] = this.books[i];
         }
         this.books = largerArray;
         System.out.println("System: Library storage doubled to " + this.books.length);
      }

      this.books[this.bookCount] = book;
      this.bookCount++;
   }

   /**
    * Retrieves the book currently selected.
    *
    * @return The current {@link Book}, or {@code null} if no book is selected.
    */
   public Book getCurrentBook() {
      if (this.currentBookIndex >= 0 && this.currentBookIndex < this.bookCount) {
         return this.books[this.currentBookIndex];
      }
      return null;
   }

   /**
    * Moves the selection to the next book.
    *
    * @return The next {@link Book}, or null if at the end of the collection.
    */
   public Book nextBook() {
      if (this.currentBookIndex + 1 < this.bookCount) {
         this.currentBookIndex++;
         return this.books[this.currentBookIndex];
      }
      return null;
   }

   /**
    * Moves the selection to the previous book.
    *
    * @return The previous {@link Book}, or {@code null} if at the start or none selected.
    */
   public Book previousBook() {
      if (this.currentBookIndex - 1 >= 0) {
         this.currentBookIndex--;
         return this.books[this.currentBookIndex];
      }
      return null;
   }

   /**
    * Returns the library's metadata.
    *
    * @return {@code String} representation of the library name and book count.
    */
   @Override
   public String toString() {
      return "Library: " + this.name + " (" + this.bookCount + " books total)";
   }

   /**
    * Entry point for testing the {@link Library} class functionality.
    * <p>
    * This method verifies:
    * <ul>
    * <li>Library and book creation.</li>
    * <li>Dynamic resizing when capacity is exceeded.</li>
    * <li>Sequential navigation and edge-case handling.</li>
    * </ul>
    * </p>
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Setup
      Library myLibrary = new Library("City Central Library", 2);

      // Books for testing
      String[] emptyPages = {"..."};
      Book b1 = new Book("Java I", "Author A", 2020, "111", emptyPages);
      Book b2 = new Book("Java II", "Author B", 2021, "222", emptyPages);
      Book b3 = new Book("Java III", "Author C", 2022, "333", emptyPages);

      // 2. Growth logic test
      System.out.println("--- Testing add & growth ---");
      myLibrary.addBook(b1);
      myLibrary.addBook(b2);
      myLibrary.addBook(b3); // This should trigger the array doubling
      System.out.println(myLibrary);

      // 3. Navigation logic test
      System.out.println("\n--- Testing navigation ---");

      // Moving Forward
      System.out.println("Moving forward:");
      while (true) {
         Book next = myLibrary.nextBook();
         if (next == null) break;
         System.out.println("  Read: " + next.title);
      }

      // Current should be at the last book
      System.out.println("Current book: " + myLibrary.getCurrentBook().title);

      // Moving Backward
      System.out.println("\nMoving backward:");
      while (true) {
         Book prev = myLibrary.previousBook();
         if (prev == null) break;
         System.out.println("  Returned to: " + prev.title);
      }

      // Final edge case check
      System.out.println("\nAttempting previousBook() at the start: " + myLibrary.previousBook());
   }
}
