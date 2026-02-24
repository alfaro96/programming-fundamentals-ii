import playerRoles.*;

/**
 * Main application class to demonstrate method overloading versus overriding.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the program.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Create instances
      Player basePlayer = new Player("Generic Athlete", 20, "Unknown");
      Forward striker = new Forward("Robert Lewandowski", 36, "Poland", 90);
      Midfielder playmaker = new Midfielder("Pedri", 22, "Spain", 15);
      Goalkeeper goalie = new Goalkeeper("Ter Stegen", 32, "Germany", 120);

      System.out.println("--- 1. Testing method overloading (Player class) ---");

      // Calls the method with no parameters.
      basePlayer.train();

      // Calls the method with an integer parameter (different signature).
      basePlayer.train(3);

      System.out.println("\n--- 2. Testing method overriding (subclasses) ---");

      // Each subclass provides a specific implementation for playMatch().
      basePlayer.playMatch(); // Parent behavior
      striker.playMatch(); // Forward specific behavior
      playmaker.playMatch(); // Midfielder specific behavior
      goalie.playMatch();  // Goalkeeper specific behavior
   }
}
