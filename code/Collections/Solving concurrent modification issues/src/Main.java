import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 * Demonstrates the correct way to modify a list during iteration
 * using an explicit {@link Iterator} and its {@link Iterator#remove()} method.
 * <p>
 * This prevents {@link java.util.ConcurrentModificationException} and maintains iterator consistency.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Main {

    /**
     * The entry point of the program.
     * <p>
     * This method creates a list of soccer player names and removes a player
     * safely during iteration using {@link Iterator#remove()}.
     * <br>
     * Unlike the incorrect approach with a {@code for-each} loop, this version avoids
     * {@link java.util.ConcurrentModificationException}.
     * </p>
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create a list of player names
        List<String> players = new ArrayList<String>();
        players.add("Cristiano Ronaldo");
        players.add("Lionel Messi");
        players.add("Luka Modric");
        players.add("Kevin De Bruyne");

        System.out.println("Original list: " + players);

        // Safely remove a player during iteration
        Iterator<String> iterator = players.iterator();
        while (iterator.hasNext()) {
            String player = iterator.next();
            if (player.equals("Luka Modric")) {
                iterator.remove(); // Safe removal using the iterator
            }
        }

        System.out.println("Updated list: " + players);
    }
}
