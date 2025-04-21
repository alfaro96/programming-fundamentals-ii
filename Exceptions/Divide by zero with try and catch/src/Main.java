/**
 * Demonstrates how to handle exceptions using a {@code try} and {@code catch} block.
 * <p>
 * This example simulates an {@link ArithmeticException} by dividing by zero,
 * handles it gracefully, and prevents the program from crashing.
 * </p>
 * <p>
 * It shows that the program can continue running after the exception is handled.
 * </p>
 * <p>
 * {@link ArithmeticException} is an unchecked exception, meaning it does not
 * need to be declared or caught to compile the program.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 * @see ArithmeticException
 */
public class Main {

    /**
     * The main method executes a simple division operation inside a {@code try} and {@code catch} block.
     * This demonstrates how to catch and handle an {@link ArithmeticException}.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            // Attempt to divide by zero, which will throw an exception
            int result = 10 / 0;
            System.out.println("Result: " + result);

        } catch (ArithmeticException e) {
            // Handle the exception and print a user-friendly message
            System.out.println("Cannot divide by zero!");
        }

        // Program continues running after the exception is handled
        System.out.println("Program continues.");
    }
}