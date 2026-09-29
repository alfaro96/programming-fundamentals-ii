import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import playerRoles.Player;

/**
 * Main execution class demonstrating the use of collection sorting.
 * <p>
 * This class serves as an entry point to illustrate how the
 * {@link Collections#sort(List)} method interacts with the
 * {@link Comparable} implementation in the {@link Player} class.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Main {

   /**
    * Application entry point.
    * <p>
    * Creates a list of players, populates it with unsorted data, and proceeds
    * to demonstrate the effectiveness of natural ordering using the {@link Collections} utility.
    * </p>
    *
    * @param args Command-line arguments (not used).
    */
   public static void main(String[] args) {
      // Using the List interface as a programming best practice
      List<Player> team = new ArrayList<Player>();

      // Adding players in random order to test the algorithm
      team.add(new Player("L. Messi", 94));
      team.add(new Player("K. Mbappé", 91));
      team.add(new Player("Vini Jr.", 89));
      team.add(new Player("E. Haaland", 92));

      System.out.println("--- List before sorting ---");
      displayTeam(team);

      // The magic happens here: Collections.sort internally uses compareTo()
      Collections.sort(team);

      System.out.println("\n--- List after orting (natural order by score) ---");
      displayTeam(team);
   }

   /**
    * Helper method to print the team members to the console.
    *
    * @param team The list of players to be displayed.
    */
   private static void displayTeam(List<Player> team) {
      for (Player p : team) {
         System.out.println(p);
      }
   }
}