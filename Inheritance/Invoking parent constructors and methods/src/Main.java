import playerRoles.*;

/**
 * Main application class to test {@code super()} constructors and methods.
 * Demonstrates the different ways subclasses invoke parent logic.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the program.
    * Executes tests to verify explicit {@code super()} calls, implicit {@code super()} calls, and {@code super.method()} logic.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      System.out.println("--- 1. Testing super(arguments) ---");
      Forward striker = new Forward("Robert Lewandowski", 36, "Poland", 90);

      System.out.println("\n--- 2. Testing implicit super() ---");
      Midfielder playmaker = new Midfielder(15);

      System.out.println("\n--- 3. Testing explicit super() ---");
      Defender centerBack = new Defender(50);

      System.out.println("\n--- 4. Testing super.method() ---");
      Goalkeeper goalie = new Goalkeeper("Marc-André ter Stegen", 32, "Germany", 120);

      System.out.println("\nTriggering goalkeeper specific action:");
      goalie.savePenalty(); // This will print the save message and then invoke the parent's celebration
   }
}
