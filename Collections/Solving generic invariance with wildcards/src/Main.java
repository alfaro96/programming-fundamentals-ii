import playerRoles.Forward;
import playerRoles.Midfielder;
import playerRoles.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates how to use wildcards to print details of different player types.
 * <p>
 * By using {@code ArrayList<? extends Player>} as the method parameter, this example allows
 * passing lists of {@link Forward}, {@link Midfielder}, or any subclass of {@link Player}.
 * </p>
 *
 * This solves the limitation of generic invariance and enables working with multiple subclasses.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Main {

    /**
     * Prints the details of each player in the given list.
     *
     * @param players a list containing any subclass of {@code Player}
     */
    public static void printPlayerDetails(List<? extends Player> players) {
        for (Player player : players) {
            System.out.println(player);
        }
    }

    /**
     * The entry point of the program.
     * Creates two type-safe lists of forwards and midfielders, and
     * prints their details using a single wildcard-based method.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Create and populate a list of forwards
        List<Forward> forwards = new ArrayList<Forward>();
        forwards.add(new Forward("Cristiano Ronaldo", 38, "Portugal", 850));
        forwards.add(new Forward("Lionel Messi", 36, "Argentina", 820));

        // Create and populate a list of midfielders
        ArrayList<Midfielder> midfielders = new ArrayList<>();
        midfielders.add(new Midfielder("Luka Modric", 37, "Croatia", 200));
        midfielders.add(new Midfielder("Kevin De Bruyne", 31, "Belgium", 250));

        // Print player details using a method that works for all player types
        System.out.println("Forwards:");
        printPlayerDetails(forwards);

        System.out.println("\nMidfielders:");
        printPlayerDetails(midfielders);
    }
}
