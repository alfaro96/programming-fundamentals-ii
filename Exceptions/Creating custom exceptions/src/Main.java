import playerRoles.Forward;
import exceptions.PlayerInjuredException;
import exceptions.TransferDeclinedException;

/**
 * The main entry point for the football simulation application.
 * <p>
 * This class orchestrates the simulation by interacting with the {@link Forward}
 * class and explicitly handling our custom domain exceptions:
 * {@link PlayerInjuredException} and {@link TransferDeclinedException}.
 */
public class Main {

    /**
     * Starts the application, instantiates a player, and demonstrates
     * the handling of both checked and unchecked custom exceptions.
     * <p>
     * It uses {@code try} and {@code catch} blocks to intercept the errors and uses
     * {@code e.toString()} to display the exact nature of the failure.
     *
     * @param args command-line arguments (not used in this simulation)
     */
    public static void main(String[] args) {
        Forward myForward = new Forward();

        System.out.println("Scenario 1: Unchecked custom exception");
        try {
            // Attempting to play a match. This invokes a method that
            // naturally throws an unchecked PlayerInjuredException.
            myForward.playMatch();
        } catch (PlayerInjuredException e) {
            // Invoking toString() prints the class name and the custom message!
            System.out.println("Main [CAUGHT]: " + e.toString());
        }

        System.out.println("\nScenario 2: Checked custom exception");
        try {
            // The compiler FORCES us to use try and catch here because
            // requestTransfer(...) explicitly declares a checked exception.
            myForward.requestTransfer("Real Madrid");
        } catch (TransferDeclinedException e) {
            // Invoking toString() prints the class name and the custom message!
            System.out.println("Main [CAUGHT]: " + e.toString());
        }
    }
}
