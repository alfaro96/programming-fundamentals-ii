import playerRoles.Player;
import playerRoles.Forward;
import playerRoles.Goalkeeper;

/**
 * Main application class to demonstrate dynamic polymorphism.
 * This class explores how Java resolves method calls at runtime based on the
 * actual object type in memory rather than the reference type.
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

      // Upcasting: References are of the general "Player" type,
      // but each points to a different specific object in memory.
      Player p1 = new Forward("Vinicius Jr.");
      Player p2 = new Goalkeeper("Thibaut Courtois");
      Player p3 = new Player("Substitute Player");

      System.out.println("--- Demonstrating dynamic binding ---");

      // Runtime decision: Although the code calls the same method "playMatch()" on "Player" references,
      // the JVM decides which implementation to run based on the actual object type currently residing in memory.
      // Dynamic binding provides massive adaptability, as the exact same method call triggers
      // completely different behaviors depending on the specific object.

      // Triggers Forward's specific implementation
      p1.playMatch();

      // Triggers Goalkeeper's specific implementation
      p2.playMatch();

      // Triggers base Player implementation
      p3.playMatch();
   }
}
