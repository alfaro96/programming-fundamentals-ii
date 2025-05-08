import java.util.ArrayList;
import playerRoles.Forward;

/**
 * Demonstrates how to use a generic {@link ArrayList} to store and manage {@link Forward} players.
 * <p>
 * By using {@code ArrayList<Forward>}, we avoid the need for casting and ensure
 * that only  {@link Forward} objects can be added to the list.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Main {

    /**
     * The main method creates a type-safe list of forwards, adds players to it,
     * and calls their specific method without casting.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
       // Create a team to hold only forward players
        ArrayList<Forward> forwards = new ArrayList<>();

        // Add forward players
        forwards.add(new Forward("Cristiano Ronaldo", 38, "Portugal", 850));
        forwards.add(new Forward("Lionel Messi", 36, "Argentina", 820));
        forwards.add(new Forward("Kylian Mbappé", 25, "France", 300));

        // Uncommenting the next line will cause a compile-time error
        // forwards.add(new Midfielder("Kevin De Bruyne", 31, "Belgium", 250));

        // Access elements directly without casting
        for (Forward forward : forwards) {
            forward.score(); // No casting needed
        }

        // Display the list of forwards
        System.out.println("\n\nTeam forwards:");
        for (Forward forward : forwards) {
            System.out.println(forward);
        }
    }
}
