import playerRoles.Forward;
import playerRoles.Player;
import java.util.ArrayList;

/**
 * Demonstrates how to use a generic {@link ArrayList} to store and manage {@link Forward} players.
 * <p>
 * Although this example uses {@code ArrayList<Forward>} to ensure type safety and avoid casting,
 * it also highlights a key limitation of generics:
 * a method that expects an {@code ArrayList<Player>} cannot accept an {@code ArrayList<Forward>}.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Main {

    /**
     * Prints the details of each player in the given list.
     * <p>
     * This method expects an {@code ArrayList<Player>} and will iterate over it,
     * printing each player's string representation.
     * </p>
     * <p>
     * Note: Due to generic type invariance, this method will not accept
     * {@code ArrayList<Forward>} or {@code ArrayList<Midfielder>} directly.
     * </p>
     *
     * @param players The list of players to print
     */
    public static void printPlayerDetails(ArrayList<Player> players) {
        for (Player player : players) {
            System.out.println(player);
        }
    }

    /**
     * The entry point of the program.
     * <p>
     * This method creates a type-safe {@code ArrayList<Forward>}, adds several players to it,
     * and calls their {@link Forward#score()} method without casting.
     * It also shows that the list can grow dynamically beyond its initial capacity.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
       // Create a team to hold only forward players
        ArrayList<Forward> forwards = new ArrayList<>();

        // Add forward players
        forwards.add(new Forward("Cristiano Ronaldo", 38, "Portugal", 850));
        forwards.add(new Forward("Lionel Messi", 36, "Argentina", 820));
        forwards.add(new Forward("Kylian Mbappé", 25, "France", 300));

        // Print the forward player details using a method that expects a list of players
        // printPlayerDetails(forwards);
    }
}
