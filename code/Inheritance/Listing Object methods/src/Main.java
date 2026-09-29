import playerRoles.Player;
import playerRoles.Forward;

/**
 * Main application class demonstrating the inherited {@link Object} toolbox.
 * Explores {@link Object#equals}, {@link Object#toString}, and {@link Object#getClass} functionality.
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
      // 1. Create an original object and a duplicate using the copy constructor
      Forward p1 = new Forward("Vinicius Jr.", 23, 15);
      Forward p2 = new Forward(p1);

      // Outputs a human-readable string instead of a memory hash.
      System.out.println("--- 1. toString() demonstration ---");
      System.out.println("Object data: " + p1.toString());

      System.out.println("\n--- 2. equals() vs identity (==) ---");

      // "==" checks if they are the exact same object in memory (false).
      System.out.println("p1 == p2 (Memory address check): " + (p1 == p2));

      // "equals()" checks if their internal data is logically equivalent (true).
      System.out.println("p1.equals(p2) (Logical equivalence): " + p1.equals(p2));

      System.out.println("\n--- 3. getClass() demonstration ---");

      // Assigning a Forward to a Player reference (upcasting)
      Player genericReference = p1;

      // Returns the actual runtime class (Forward), regardless of the reference type (Player).
      System.out.println("Runtime class: " + genericReference.getClass().getSimpleName());

      // We can use this for strict type verification before downcasting.
      if (genericReference.getClass() == Forward.class) {
         System.out.println("Verification passed. Safe to downcast to Forward.");
      }
   }
}
