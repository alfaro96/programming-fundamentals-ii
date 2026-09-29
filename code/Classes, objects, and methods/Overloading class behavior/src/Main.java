/**
 * Main application class to demonstrate the flexibility of constructor
 * and method overloading.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the application.
    * Shows how the compiler distinguishes between different signatures.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Initializing with a random-style identifier using the full constructor
      Player p1 = new Player("af4f9ac3-76a6-4ac4-a6a7-9747592d7ffd", "Lionel Messi", 38, "Argentina");

      // 2. Initializing without an identifier using the overloaded constructor
      Player p2 = new Player("Cristiano Ronaldo", 40, "Portugal");

      // 3. Executing a standard training session (timed)
      p1.train("Free kicks", 45);

      // 4. Executing an overloaded training session (quick)
      p2.train("Sprints");

      // 5. Outputting results to verify the state
      System.out.println("Player 1 unique registry: " + p1.id);
      System.out.println("Player 2 unique registry: " + p2.id);
   }
}
