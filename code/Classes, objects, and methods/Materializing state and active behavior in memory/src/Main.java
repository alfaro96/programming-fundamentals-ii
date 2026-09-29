/**
 * Main application class to demonstrate object instantiation and memory management.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * The entry point of the application.
    * It demonstrates the transition of an object from {@code null} to an active state.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Declaration without initialization
      Player player = null;
      System.out.println("Current state of player: " + player);

      int goals = 10;
      System.out.println(goals);

      // 2. Accessing a null reference (this would cause an error)
      // player.name = "Cristiano Ronaldo";

      // 3. Proper instantiation using "new"
      player = new Player();
      System.out.println("Object materialized in memory.");

      // 4. Accessing and modifying attributes
      player.name = "Lionel Messi";
      player.age = 36;
      player.nationality = "Argentina";

      System.out.println("Player name: " + player.name);
      System.out.println("Player age: " + player.age);
      System.out.println("Player nationality: " + player.nationality);

      Player player2 = new Player();
      int age = 0;
      player2.age = 18;
      player2.nationality = "Morocco";
      player2.name = "Lamine Yamal";

      System.out.println("Player name: " + player2.name);
      System.out.println("Player age: " + player2.age);
      System.out.println("Player nationality: " + player2.nationality);
   }
}
