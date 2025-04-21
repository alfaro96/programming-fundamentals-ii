/**
 * Demonstrates how to use the {@code throw} keyword to manually raise an exception.
 * <p>
 * This example checks if a divisor is zero and throws an {@link ArithmeticException}
 * with a custom message if so.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 * @see ArithmeticException
 */
public class Main {

    /**
     * Divides two integers. If the divisor is zero, an {@link ArithmeticException} is thrown manually.
     *
     * @param a the dividend
     * @param b the divisor
     */
    public static void divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        System.out.println("Result: " + (a / b));
    }

    /**
     * Main method to test the divide operation with valid and invalid input.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        divide(10, 2); // Safe division
        divide(5, 0); // Will throw an exception
    }
}
