import playerRoles.*;

/**
 * Main application class to test the {@link Player} inheritance hierarchy.
 * Demonstrates how subclasses inherit attributes and methods from their superclass
 * while maintaining their own specific behaviors.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the program.
    * Creates instances of specific player roles and verifies their access
    * to inherited members and specific attributes.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Instantiate subclasses using their specific constructors
      Forward striker = new Forward("Leo Messi", 38, "Argentina", 850);
      Midfielder playmaker = new Midfielder("Kevin De Bruyne", 33, "Belgium", 250);
      Defender centerBack = new Defender("Virgil van Dijk", 33, "Netherlands", 500);
      Goalkeeper goalie = new Goalkeeper("Alisson Becker", 32, "Brazil", 300);

      // 2. Verify access to inherited attributes (name, nationality) and specific attributes
      System.out.println("--- Player information ---");
      System.out.println(striker.name + " (" + striker.nationality + ") - Goals: " + striker.goals);
      System.out.println(playmaker.name + " (" + playmaker.nationality + ") - Assists: " + playmaker.assists);
      System.out.println(centerBack.name + " (" + centerBack.nationality + ") - Tackles: " + centerBack.tackles);
      System.out.println(goalie.name + " (" + goalie.nationality + ") - Saves: " + goalie.saves);

      // 3. Verify access to inherited methods
      System.out.println("\n--- Inherited behaviors ---");
      striker.celebrate();
      playmaker.celebrate();
      centerBack.celebrate();
      goalie.celebrate();
   }
}
