import java.io.FileNotFoundException;
import playerRoles.Forward;

/**
 * The main entry point for the football simulation application.
 * <p>
 * This class demonstrates how the compiler enforces different rules
 * for checked versus unchecked exceptions.
 */
public class Main {

   /**
    * Starts the application and handles player data.
    *
    * @param args command-line arguments (not used)
    */
   public static void main(String[] args) {
      Forward myForward = new Forward();

      System.out.println("Main: Preparing the player for the match.");

      // Checked exception
      // The compiler strictly FORCES us to use try-catch here.
      // If you remove the try-catch block, the code will turn red and will not compile!
      try {
         myForward.loadTactics();
      } catch (FileNotFoundException e) {
         System.out.println("Main [RECOVERY]: Tactics file not found. Using default tactics.");
      }

      System.out.println("\nMain: Match finished. Reviewing statistics.");

      // Unchecked exception
      // The compiler does NOT force us to use try and catch here.
      // It compiles perfectly fine, but because we are passing a 0,
      // it will crash the program at runtime (programming logic error).
      int accuracy = myForward.calculateAccuracy(10, 0);

      // This line will not be reached due to the runtime crash above.
      System.out.println("Main: The accuracy is: " + accuracy);
   }
}
