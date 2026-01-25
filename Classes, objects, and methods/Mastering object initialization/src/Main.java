/**
 * Main application class to demonstrate object initialization using constructors.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the application.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Efficient initialization
      // We pass the arguments directly to the constructor in a single line
      Player player = new Player("Lionel Messi", 36, "Argentina");

      // The object is ready to use immediately
      player.celebrate();
      player.train("Free kicks", 15);

      int yearsToWorldCup = player.calculateYearsToAge(39);
      System.out.println("Years until age 39: " + yearsToWorldCup);

      // 2. The default constructor disappears
      // Since we defined a custom constructor, the following line would cause a compilation error:
      // Player emptyPlayer = new Player();
   }
}