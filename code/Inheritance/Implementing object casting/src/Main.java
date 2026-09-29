import playerRoles.Player;
import playerRoles.Forward;

/**
 * Main application class demonstrating object casting.
 * Explores upcasting implicitly versus downcasting explicitly using soccer roles.
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
      // Upcasting (implicit and safe)

      // Primitive analogy: Moving up to a wider type is safe and implicit.
      // int smallNumber = 10;
      // double largeContainer = smallNumber; // Implicit cast to 10.0

      // The object is assigned to a reference of a more general type.
      // This is safe because a Forward is always a valid instance of Player.
      Player player = new Forward("Cristiano Ronaldo", 38, "Portugal", 850);
      System.out.println("Player created: " + player.getName());

      // Compilation error: Cannot resolve method "scoreGoal" in "Player".
      // Even though the actual object is a "Forward", the compiler only sees the "Player" reference type.
      // player.scoreGoal();

      System.out.println();

      // Downcasting (explicit and risky)

      // Primitive analogy: Moving down to a narrower type requires explicit cast due to risk of data loss.
      // double preciseNumber = 10.99;
      // int forcedNumber = (int) preciseNumber; // Explicit cast to 10

      // The reference is being converted to a more specific type.
      // This must be explicit because it carries risk.
      Forward forward = (Forward) player;

      // Object identity: No new object is created; the same instance is referenced with a different type.
      forward.scoreGoal();
   }
}
