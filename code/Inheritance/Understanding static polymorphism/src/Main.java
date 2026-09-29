import playerRoles.Player;

/**
 * Main application class to demonstrate static polymorphism.
 * Shows how the compiler resolves which method to call at compile time.
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
      Player player = new Player("Lamine Yamal");

      System.out.println("--- Demonstrating static binding ---");

      // The compiler determines which method to execute based on the arguments provided.

      // Matches train()
      player.train();

      // Matches train(int)
      player.train(45);

      // Matches train(String)
      player.train("Dribbling");
   }
}
