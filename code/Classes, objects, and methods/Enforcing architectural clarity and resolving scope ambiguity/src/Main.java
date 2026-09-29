/**
 * Main application class to demonstrate the {@code this} reference.
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
        Player player = new Player("Lionel Messi", 36, "Argentina");

        // The object uses its internal state correctly thanks to "this"
        player.scoreGoal();

        int gap = player.calculateYearsToAge(40);
        System.out.println("Remaining years: " + gap);
    }
}