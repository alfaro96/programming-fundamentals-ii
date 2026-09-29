import playerRoles.FieldPlayer;
import playerRoles.Player;
import playerRoles.Forward;

/**
 * Main application class demonstrating abstract blueprints and their instantiation rules.
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

      System.out.println("--- 1. Instantation rules ---");

      // You cannot instantiate an abstract class directly to create an object.
      // The following line would cause a compilation error if uncommented.
      // Player genericPlayer = new Player("Unknown");

      System.out.println("\n--- 2. Using concrete subclasses ---");

      // We can use the concrete subclass to create an object.
      Forward striker = new Forward("Erling Haaland", 35);

      // Calling the fully implemented method (mixed content).
      striker.train();

      // Calling the method that the concrete subclass was forced to implement.
      double bonus = striker.calculateBonus();
      System.out.println(striker.getName() + " earned a bonus of: " + bonus + " euros");
   }
}