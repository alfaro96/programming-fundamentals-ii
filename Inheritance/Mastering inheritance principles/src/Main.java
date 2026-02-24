import playerRoles.*;

/**
 * Main application class to test advanced inheritance principles.
 * This class verifies the concepts of full lineage and class hierarchies.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the program.
    * Creates a {@link Striker} instance and prints attributes from every level of its lineage
    * to demonstrate how subclasses inherit from all their superclasses.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // Instantiate the final subclass (level 2 in the hierarchy)
      Striker pureStriker = new Striker("Erling Haaland", 24, "Norway", 100, 99);

      System.out.println("=== FULL LINEAGE DEMONSTRATION ===");
      System.out.println("Tracing the inheritance tree for: " + pureStriker.name + "\n");

      // 1. Level 0: Inherited from Player
      System.out.println("--- Inherited from superclass: Player ---");
      System.out.println("Name: " + pureStriker.name);
      System.out.println("Age: " + pureStriker.age);
      System.out.println("Nationality: " + pureStriker.nationality);

      // 2. Level 1: Inherited from Forward
      System.out.println("\n--- Inherited from superclass: Forward ---");
      System.out.println("Goals: " + pureStriker.goals);

      // 3. Level 2: Specific to Striker
      System.out.println("\n--- Specific to class: Striker ---");
      System.out.println("Shot Power: " + pureStriker.shotPower);

      // 4. Inherited behaviors
      System.out.println("\n=== INHERITED BEHAVIORS ===");
      System.out.print("Action from Player class: ");
      pureStriker.celebrate();
   }
}