import playerRoles.Forward;

/**
 * The main entry point for the football simulation application.
 * <p>
 * This class acts as the final caller. Since the {@link Forward} class passed the
 * exception up the chain, the main method must take responsibility and handle it.
 */
public class Main {

    /**
     * Starts the application and handles any exceptions passed up the chain.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Forward myForward = new Forward();
        int finalScore = 0;

        // Main takes responsibility! We wrap the call in a try block.
        try {
            // We pass "0" for matchesPlayed, triggering the error in Player,
            // which passes through Forward, and arrives here.
            finalScore = myForward.getAttackingScore(5, 450, 0);

        } catch (ArithmeticException e) {
            // Main intercepts the exception that traveled up the chain.
            System.out.println("Main [ERROR CAUGHT]: The player hasn't played any matches yet.");
            System.out.println("Main: Assigning a default score of 0.");
        }

        System.out.println("Main: The final attacking score is: " + finalScore);
    }
}