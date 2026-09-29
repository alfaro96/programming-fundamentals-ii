/**
 * Main application class to demonstrate the utility of Wrapper classes.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the application.
    * Shows practical examples of {@link Integer}, {@link Double}, and {@link Character} utilities.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Parsing: Converting Strings to numbers
      String scoreText = "450";
      int score = Integer.parseInt(scoreText);
      System.out.println("Parsed score: " + (score + 50));

      // 2. Constants: Knowing the limits
      System.out.println("Integer maximum Value: " + Integer.MAX_VALUE);
      System.out.println("Integer minimum Value: " + Integer.MIN_VALUE);

      // 3. Character utilities: Analyzing symbols
      char symbol = '7';
      boolean isANumber = Character.isDigit(symbol);
      boolean isALetter = Character.isLetter(symbol);

      System.out.println("Is '" + symbol + "' a digit? " + isANumber);
      System.out.println("Is '" + symbol + "' a letter? " + isALetter);

      // 4. Autoboxing: Seamless conversion
      // Automatically wraps the primitive "double" into a "Double" object
      Double priceObject = 19.99;
      double pricePrimitive = priceObject; // Unboxing

      System.out.println("Object price: " + priceObject);
      System.out.println("Primitive price: " + pricePrimitive);
   }
}
