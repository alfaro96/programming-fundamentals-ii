import playerRoles.Forward;

/**
 * The main entry point for the football simulation application.
 * <p>
 * This class attempts to provide invalid data to the {@link Forward} class
 * and catches the manually thrown exception object.
 */
public class Main {

    /**
     * Starts the application and handles the custom error message.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Forward myForward = new Forward();

        System.out.println("Main: Updating player stats after the match.");

        try {
            // We intentionally pass a negative value to trigger our manual throw
            myForward.setEnergyLevel(-15);

        } catch (IllegalArgumentException e) {
            // We catch the object we created in the Forward class
            // and use e.getMessage() to extract the custom String we passed to it.
            System.out.println("Main [ERROR CAUGHT]: " + e.getMessage());
            System.out.println("Main: Reverting to safe default values.");
        }

        System.out.println("Main: Player evaluation complete.");
    }
}