import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Works with the list of colors of Exercise 1, now a {@code List<String>}, so the compiler checks
 * the type of every element.
 *
 * <p>Each step that changes the list prints it, as in Exercise 1. The integer that the list of
 * Exercise 1 accepted no longer compiles, and the string at position 3 needs no cast.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see List
 * @see Collections
 */
public class Main {

    /**
     * Adds, inserts, replaces, removes and sorts colors in a list of strings, printing it after
     * each step.
     *
     * @param args the command-line arguments, not used.
     */
    public static void main(String[] args) {
        List<String> colors = new ArrayList<>();

        colors.add("Red");
        colors.add("Green");
        colors.add("Orange");
        colors.add("White");
        colors.add("Black");
        System.out.println("Step 3 - Initial list: " + colors);

        colors.add(0, "Pink");
        System.out.println("Step 4 - After inserting Pink at 0: " + colors);

        colors.set(3, "Blue");

        colors.add("Purple");
        System.out.println("Step 6 - After adding Purple at the end: " + colors);

        System.out.print("Step 7 - Elements at even positions: ");
        for (int i = 0; i < colors.size(); i += 2) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(colors.get(i));
        }
        System.out.println();

        colors.remove(4);
        System.out.println("Step 8 - After removing the element at position 4: " + colors);

        Collections.sort(colors);
        System.out.println("Step 9 - Sorted list: " + colors);

        // Main.java: error: incompatible types: int cannot be converted to String
        // colors.add(100);

        String color = colors.get(3);
        System.out.println("Step 11 - Length of the string at position 3: " + color.length());
    }
}
