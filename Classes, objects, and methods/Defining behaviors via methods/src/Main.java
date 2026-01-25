/**
 * Main application class to test object behaviors and signature validation.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the application.
    * Demonstrates correct invocations and documents common calling errors.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Instantiation and manual initialization
      Player player = new Player();
      player.name = "Lionel Messi";
      player.age = 36;
      player.nationality = "Argentina";

      // 2. Correct invocations

      // No arguments
      player.celebrate();

      // With arguments
      player.train("Tactics", 45);

      // Capturing a return value
      int yearsLeft = player.calculateYearsToAge(40);
      System.out.println("Years until age 40: " + yearsLeft);

      // 3. Erroneous invocations (commented out to prevent compilation failure)

      // Different number of arguments
      // player.train("Sprinting");

      // Same number but different type
      // player.train("Sprinting", "Sixty");

      // Different order of arguments
      // player.train(60, "Sprinting");
   }
}
