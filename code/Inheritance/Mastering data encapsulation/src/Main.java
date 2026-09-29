import playerRoles.Player;
import playerRoles.Forward;
import externalClub.LoanedPlayer;
import matchAnalysis.PerformanceAnalyst;

/**
 * Main application class to demonstrate encapsulation and access modifiers.
 * Instantiates objects from different packages to test the visibility rules.
 * Since {@link Main} is in the default package, it acts as "Another class from a different package".
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the program.
    * Executes tests to verify the four levels of access modifiers across different packages and subclasses.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Instantiate the base Player (full access constructor)
      Player basePlayer = new Player("Lamine Yamal", 17, "Spain", "Fit");

      System.out.println("--- Testing access ---");
      System.out.println("Public name: " + basePlayer.name); // Public: Accessible everywhere.

      // Protected: Main is not a subclass of Player and is not in "playerRoles".
      // System.out.println("Protected age: " + basePlayer.age);

      // Default: Main is not in the "playerRoles" package.
      // System.out.println("Default nationality: " + basePlayer.nationality);

      // Private: Main is not the Player class.
      // System.out.println("Private medical: " + basePlayer.medicalCondition);

      System.out.println();

      // 2. Same package subclass (accesses public, protected, and default implicitly)
      Forward localForward = new Forward("Robert Lewandowski", 36, "Poland", "Fit", 90);
      System.out.println("Created local forward: " + localForward.name + " with " + localForward.goals + " goals.");

      // 3. Different package subclass (only accesses public and protected)
      LoanedPlayer loaned = new LoanedPlayer("Ansu Fati", 22, "Spain", "Injured", "FC Barcelona", true);
      loaned.printLoanDetails();

      System.out.println();

      // 4. Different package non-subclass (only accesses public)
      PerformanceAnalyst analyst = new PerformanceAnalyst("Maldini");
      analyst.evaluatePlayer(basePlayer);

      // 5. Demonstrating how to properly access private data using a public method
      System.out.println("--- Secure data access ---");
      basePlayer.printMedicalRecord();
   }
}
