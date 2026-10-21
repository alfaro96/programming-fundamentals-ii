import java.util.ArrayList;
import java.util.Collections;

/**
 * Works with a list of colors kept in an {@link ArrayList} with no type of element, which takes
 * any object.
 *
 * <p>Each step that changes the list prints it, so the output shows how the positions of the
 * elements shift as they are inserted, replaced and removed.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see ArrayList
 * @see Collections
 */
public class Main {

    /**
     * Adds, inserts, replaces, removes and sorts colors in a list, printing it after each step.
     *
     * @param args the command-line arguments, not used.
     */
    public static void main(String[] args) {
        ArrayList colors = new ArrayList();

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

        colors.add(100);
        System.out.println("Step 10 - After adding an integer: " + colors);

        String color = (String) colors.get(3);
        System.out.println("Step 11 - Length of the string at position 3: " + color.length());
    }
}
