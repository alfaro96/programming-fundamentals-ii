import java.util.ArrayList;
import playerRoles.Forward;
import playerRoles.Midfielder;

/**
 * Demonstrates the use of an {@link ArrayList} to store and manage soccer players of different types.
 * <p>
 * This example shows how to:
 * <ul>
 *     <li>Create a list of objects from unrelated classes</li>
 *     <li>Use {@code instanceof} and casting to access class-specific methods</li>
 *     <li>Print details of each player using overridden {@code toString} methods</li>
 * </ul>
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Main {

    /**
     * The main method where the program execution begins.
     * It creates an {@link ArrayList} of soccer players, invokes specific
     * actions based on player type, and displays player details.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create a team to hold various types of soccer players with an initial capacity for three players
        ArrayList team = new ArrayList(3);

        // Add some forwards and midfielders to the team (more than initial capacity to show dynamic resizing)
        team.add(new Forward("Cristiano Ronaldo", 38, "Portugal", 850));
        team.add(new Midfielder("Luka Modric", 37, "Croatia", 200));
        team.add(new Forward("Lionel Messi", 36, "Argentina", 820));
        team.add(new Midfielder("Kevin De Bruyne", 31, "Belgium", 250));

        // Iterate through the list using a standard for loop to invoke role-specific methods
        for (int i = 0; i < team.size(); i++) {
            // Access elements by index, just like with a regular array
            Object player = team.get(i);

            // Check the type and call the appropriate action
            if (player instanceof Forward) {
                ((Forward) player).score(); // Calls the forward's score method
            } else if (player instanceof Midfielder) {
                ((Midfielder) player).assist(); // Calls the midfielder's assist method
            }
        }

        // Print a header before showing team details
        System.out.println("\nTeam roster:");

        // Display the details of each player using their overridden toString method
        for (Object player : team) {
            System.out.println(player);
        }

        // Replace the player at index zero with another forward
        team.set(0, new Forward("Kylian Mbappé", 25, "France", 300));

        // Insert a new midfielder at position two (other elements shift right)
        Midfielder iniesta = new Midfielder("Andrés Iniesta", 39, "Spain", 180);
        team.add(2, iniesta);

        // Check if the list contains a particular player, find his index, and call his assist method
        if (team.contains(iniesta)) {
            int index = team.indexOf(iniesta);
            ((Midfielder) team.get(index)).assist();
        }

        // Remove the player at index two
        team.remove(2);

        // Display the remaining players and their details
        System.out.println("\nUpdated team:");
        for (int i = 0; i < team.size(); i++) {
            System.out.println(team.get(i));
        }

        // Clear the entire list and check if it’s empty
        team.clear();
        System.out.println("\nIs the team empty? " + team.isEmpty());
    }
}
