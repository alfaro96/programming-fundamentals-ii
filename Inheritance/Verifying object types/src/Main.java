import playerRoles.Player;
import playerRoles.Forward;
import playerRoles.Goalkeeper;

/**
 * Main application class demonstrating how to verify object types.
 * Explores the risks of unsafe downcasting and how to prevent runtime crashes 
 * using the {@code instanceof} operator.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the program.
    *
    * @param args Command line arguments.
    */
   public static void main(String[] args) {
      // We create a generic Player reference, but point it to a Goalkeeper object
      Player genericPlayer = new Goalkeeper("Iker Casillas", 40, "Spain", 500);

      // Unsafe downcasting
      // Forward wrongCast = (Forward) genericPlayer;
      // wrongCast.scoreGoal();

      System.out.println("--- Safe downcasting with instanceof ---");

      if (genericPlayer instanceof Forward) {
         Forward safeForward = (Forward) genericPlayer;
         safeForward.scoreGoal();
      } else {
         System.out.println("Safety check failed: " + genericPlayer.getName() + " is not a Forward. Skipping cast.");
      }

      // Let's test it again with an actual Forward to see the success path
      Player actualForward = new Forward("Karim Benzema", 35, "France", 400);

      if (actualForward instanceof Forward) {
         System.out.println("\nMatch found! Safely casting " + actualForward.getName() + ".");
         Forward safeForward = (Forward) actualForward;
         safeForward.scoreGoal();
      }
   }
}