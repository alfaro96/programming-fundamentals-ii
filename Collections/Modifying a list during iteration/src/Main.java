import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Demonstrates a common mistake: modifying a list directly during iteration using a {@code for-each} loop,
 * which results in a {@link ConcurrentModificationException}.
 * <p>
 * This example shows why you should not modify a collection inside a {@code for-each} loop.
 * Instead, use an explicit {@link Iterator} and its {@link Iterator#remove()} method to safely remove elements.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Main {

    /**
     * The entry point of the program.
     * <p>
     * This method creates a list of soccer player names and attempts to remove elements
     * from the list using a {@code for-each} loop.
     * This triggers a {@link ConcurrentModificationException}, because modifying the list directly
     * during iteration invalidates the loop’s internal iterator.
     * </p>
     * <p>
     * This highlights the importance of using an explicit {@link Iterator}
     * with its {@link Iterator#remove()} method to safely modify collections during iteration.
     * </p>
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a list of player names
        ArrayList<String> players = new ArrayList<>();
        players.add("Cristiano Ronaldo");
        players.add("Lionel Messi");
        players.add("Luka Modric");
        players.add("Kevin De Bruyne");

        System.out.println("Original list: " + players);

        // Modifying the list during iteration
        for (String item : players) {
            players.remove(item);
        }
    }
}
