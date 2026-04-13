import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Main class for Exercise 2.
 * <p>
 * This class repeats the steps from Exercise 1 but utilizes a generic
 * {@link ArrayList} (specifically {@code List<String>}). This ensures
 * type safety at compile time and eliminates the need for manual casting
 * when retrieving elements.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see java.util.ArrayList
 * @see java.util.Collections
 */
public class Main {

   /**
    * The entry point of the application. All exercise steps are executed
    * sequentially here without relying on external methods.
    *
    * @param args Command-line arguments (not used).
    */
   public static void main(String[] args) {
      // 1. Create a generic ArrayList named colors, initially empty.
      // Notice the use of <String> to enforce type safety.
      List<String> colors = new ArrayList<String>();

      // 2. Add the following color names (as String objects).
      colors.add("Red");
      colors.add("Green");
      colors.add("Orange");
      colors.add("White");
      colors.add("Black");

      // 3. Print the content of the ArrayList.
      System.out.println("Step 3 - Initial list: " + colors);

      // 4. Insert the string "Pink" at position 0. Print to verify.
      colors.add(0, "Pink");
      System.out.println("Step 4 - After inserting Pink at 0: " + colors);

      // 5. Replace the element at position 3 (currently "Orange") with "Blue".
      colors.set(3, "Blue");

      // 6. Add "Purple" at the end. Print to verify.
      colors.add("Purple");
      System.out.println("Step 6 - After adding Purple at the end: " + colors);

      // 7. Print elements at even positions.
      System.out.print("Step 7 - Elements at even positions: ");
      for (int i = 0; i < colors.size(); i += 2) {
         System.out.print(colors.get(i) + (i + 2 < colors.size() ? ", " : ""));
      }
      System.out.println();

      // 8. Remove the element at position 4. Print to verify.
      colors.remove(4);
      System.out.println("Step 8 - After removing element at position 4: " + colors);

      // 9. Sort the list alphabetically using Collections.sort(...). Print to verify.
      Collections.sort(colors);
      System.out.println("Step 9 - Sorted list: " + colors);

      // 10. Add an integer to the list. Print to verify.
      colors.add(String.valueOf(100));
      System.out.println("Step 10 - After adding an integer (as a String): " + colors);

      // 11. Print the length of the string at position 3.
      String elementAt3 = colors.get(3);
      System.out.println("Step 11 - Length of string at position 3 ('" + elementAt3 + "'): " + elementAt3.length());
   }
}
