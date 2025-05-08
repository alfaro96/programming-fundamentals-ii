import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Demonstrates how to use a {@link ListIterator} to traverse an {@link ArrayList} in multiple ways.
 * <p>
 * This example highlights the following traversal modes:
 * <ul>
 *     <li><strong>Forward traversal:</strong> Iterating from the beginning to the end of the list using {@link ListIterator#hasNext()} and {@link ListIterator#next()}.</li>
 *     <li><strong>Backward traversal:</strong> Iterating from the end to the beginning using {@link ListIterator#hasPrevious()} and {@link ListIterator#previous()}.</li>
 *     <li><strong>Partial traversal:</strong> Starting iteration from a specific index using {@link ArrayList#listIterator(int index))}.</li>
 * </ul>
 * This example showcases the added flexibility provided by {@link ListIterator} compared to a standard {@link Iterator}.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Main {

    /**
     * The entry point of the program.
     * <p>
     * This method creates an {@link ArrayList} of strings and demonstrates three different ways to traverse it using a {@link ListIterator}:
     * <ol>
     *     <li>Traversing the list from start to end.</li>
     *     <li>Traversing the list in reverse order.</li>
     *     <li>Traversing from a specific starting index (in this case, index 2).</li>
     * </ol>
     * The contents of the list are printed in each traversal.
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

        // Get an iterator for the list
        ListIterator<String> iterator = players.listIterator();

        // Traverse forward
        System.out.println("Forward:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Traverse backward
        System.out.println("\nBackward:");
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }

        // Traverse from a specific index (start at position 2)
        System.out.println("\n\nFrom index 2:");
        ListIterator<String> fromIndex = players.listIterator(2);
        while (fromIndex.hasNext()) {
            System.out.println(fromIndex.next());
        }
    }
}
