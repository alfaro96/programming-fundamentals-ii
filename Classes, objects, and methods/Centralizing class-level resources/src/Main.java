/**
 * Main application class to test class-level resources.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the application.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Accessing static behavior without any objects
      // Player.displayGlobalCount();

      // 2. Creating multiple players
      Player p1 = new Player("Lionel Messi", 36, "Argentina");
      Player p2 = new Player("Cristiano Ronaldo", 38, "Portugal");

      // 3. Confirming the count has updated globally
      Player.displayGlobalCount();

      // 4. Using instance methods to see the shared data
      // Each player object reports its own name but the same global count
      p1.displayRegistryInfo();
      p2.displayRegistryInfo();
   }
}
