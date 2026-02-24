import playerRoles.Forward;
import playerRoles.Goalkeeper;

/**
 * Main application class to demonstrate the use of getters and setters.
 * Tests data retrieval and validation rules implemented inside mutator methods.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the program.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Instantiate objects using constructors
      Forward striker = new Forward("Robert Lewandowski", 36, "Poland", 90);
      Goalkeeper goalie = new Goalkeeper("Marc-André ter Stegen", 32, "Germany", 120);

      System.out.println("--- Initial states using getters ---");

      // Using inherited getters
      System.out.println("Striker name: " + striker.getName());
      System.out.println("Striker age: " + striker.getAge());

      System.out.println("\nGoalie name: " + goalie.getName());

      // Using specific getters
      System.out.println("Striker goals: " + striker.getGoals());
      System.out.println("Goalie saves: " + goalie.getSaves());

      System.out.println("\n--- Updating values using setters ---");

      // Simulating a scored goal using get and set
      striker.setGoals(striker.getGoals() + 1);
      System.out.println("Lewandowski scored! New goals count: " + striker.getGoals());

      // Simulating a birthday
      striker.setAge(37);
      System.out.println("Happy Birthday! New age: " + striker.getAge());

      System.out.println("\n--- Testing validation in setters ---");

      // Attempting to set an invalid age to show the benefit of encapsulation
      System.out.println("Trying to set age to -5");
      striker.setAge(-5);
      System.out.println("Age remains safely as: " + striker.getAge());
   }
}
