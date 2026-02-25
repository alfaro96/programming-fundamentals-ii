import soccerRoles.*;

/**
 * Main application demonstrating extreme polymorphism with interfaces.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the program.
    *
    * @param args Command line arguments.
    */
   public static void main(String[] args) {
      Forward striker = new Forward("Erling Haaland", 35);
      Coach headCoach = new Coach("Pep Guardiola", 15);

      System.out.println("--- 1. Universal grouping (extreme polymorphism) ---");

      // You can group completely unrelated classes from different
      // families if they share the same interface.
      Interviewable[] pressConference = new Interviewable[2];
      pressConference[0] = striker; // Upcasting to interface reference
      pressConference[1] = headCoach; // Upcasting to interface reference

      for (Interviewable person : pressConference) {
         // Polymorphic call: Java decides at runtime which implementation to execute
         person.giveInterview();
      }

      System.out.println("\n--- 2. Downcasting from interface references ---");

      // You can safely perform upcasting and downcasting using the interface
      // as the reference type, just like you would with superclasses.
      Interviewable interviewee = pressConference[0]; // This is actually the Forward

      // Check if this specific interviewee also implements the Sponsorable contract
      if (interviewee instanceof Sponsorable) {
         Sponsorable sponsoredAthlete = (Sponsorable) interviewee;
         System.out.println("This interviewee also has a sponsorship worth: " + sponsoredAthlete.calculateSponsorship() + " euros");
      }
   }
}
