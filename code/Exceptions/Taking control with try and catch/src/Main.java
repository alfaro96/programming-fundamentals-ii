import playerRoles.Forward;

/**
 * The main entry point for the football simulation application.
 * <p>
 * This class demonstrates how to safely handle a potentially dangerous method
 * using try and catch, preventing the application from crashing.
 */
public class Main {

    /**
     * Starts the application and attempts to calculate player statistics safely.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Forward myForward = new Forward();
        int accuracy = 0; // We set a safe default value

        // The try block wraps the code that might fail
        try {
            // We pass "0" as the second argument, which will trigger the exception
            accuracy = myForward.calculateAccuracy(10, 0);

        } catch (ArithmeticException e) {
            // The catch block intercepts the crash and runs this safe alternative
            System.out.println("ERROR INTERCEPTED: Cannot calculate accuracy with zero missed shots.");
            System.out.println("Applying default accuracy value.");
        }

        // Because we caught the exception, the program survives and reaches this line!
        System.out.println("The calculated accuracy is: " + accuracy);
        System.out.println("The program finished successfully.");
    }
}
