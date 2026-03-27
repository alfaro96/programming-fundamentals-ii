import playerRoles.Forward;

/**
 * The main entry point for the football simulation application.
 * <p>
 * This class demonstrates what happens when we invoke a method 
 * that declares a risk, but we ignore the warning and provide bad data.
 */
public class Main {

    /**
     * Starts the application and attempts to calculate player statistics.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Forward myForward = new Forward();

        // The calculateAccuracy method warns us with "throws ArithmeticException".
        // By passing "0" as the second argument without handling the exception,
        // the program will crash and stop running immediately.
        int accuracy = myForward.calculateAccuracy(10, 0);

        // This line will never be reached because the program crashes above!
        System.out.println("The accuracy is: " + accuracy);
    }
}
