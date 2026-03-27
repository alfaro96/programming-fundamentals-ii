/**
 * The main entry point for the football simulation application.
 * <p>
 * This class demonstrates the use of a {@code try-catch-finally} structure
 * to ensure external resources are safely closed even when errors occur.
 */
public class Main {

    /**
     * Starts the application and handles the reporting process.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        MatchReporter reporter = new MatchReporter();

        System.out.println("Main: Post-match reporting sequence initiated.");

        // We open the connection before the risky operations begin
        reporter.openConnection();

        try {
            // We pass valid data so no exception is thrown
            reporter.saveStats(3);

            System.out.println("Main: Data processing complete. Exiting method early!");

            // Attempting to exit
            // We use return to try and stop the method right now.
            return;

        } catch (IllegalArgumentException e) {
            // We catch the error to prevent a total crash
            System.out.println("Main [ERROR CAUGHT]: " + e.getMessage());

        } finally {
            // Guaranteed execution
            // This block runs no matter what happened above.
            // It ensures the connection is not left open and hanging.
            System.out.println("Main [FINALLY BLOCK]: Executing cleanup operations.");
            reporter.closeConnection();
        }


        System.out.println("Main: Program terminated gracefully.");
    }
}