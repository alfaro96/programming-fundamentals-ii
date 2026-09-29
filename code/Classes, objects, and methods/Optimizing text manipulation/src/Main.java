/**
 * Main application class to demonstrate efficient text manipulation.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

   /**
    * Entry point of the application.
    * Compares {@link String} concatenation with the efficiency of {@link StringBuilder}.
    *
    * @param args Command line arguments (not used).
    */
   public static void main(String[] args) {

      // 1. Standard String (fixed text)
      // Every time we use "+", a brand-new object is created in memory
      String staticText = "Hello";
      staticText += " World";
      System.out.println("Static result: " + staticText);

      // 2. StringBuilder (dynamic construction)
      // This modifies the SAME object in memory without creating copies
      StringBuilder builder = new StringBuilder("Starting report");

      // Adding content
      builder.append(" - Status: OK");
      builder.append(" - Progress: ").append(100).append("%");

      // Modifying content
      builder.insert(0, "[URGENT] "); // Add to the beginning
      builder.replace(9, 17, "MESSAGE"); // Change "Starting" to "MESSAGE"

      System.out.println("Builder result: " + builder);

      // 3. Fun utilities: Reversing text
      StringBuilder secret = new StringBuilder("Java Programming");
      secret.reverse();
      System.out.println("Reversed text: " + secret);

      // 4. Practical use case: Building a list
      StringBuilder list = new StringBuilder("Shopping list:");
      String[] items = {"Milk", "Bread", "Eggs"};

      for (String item : items) {
         list.append("\n * ").append(item);
      }

      String finalResult = list.toString();
      System.out.println(finalResult);
   }
}
