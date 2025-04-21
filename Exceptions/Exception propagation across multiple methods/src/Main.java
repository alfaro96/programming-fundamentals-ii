import java.io.IOException;

/**
 * Demonstrates how exception propagation works.
 * <p>
 * This example has a chain of method calls: {@link #methodC} → {@link #methodB} → {@link #methodA} → {@link #main}.
 * {@link #methodC} throws an {@link IOException}, and the exception is not handled until it
 * reaches the {@link #main} method.
 * </p>
 * <p>
 * This shows how exceptions can be propagated up the call stack using {@code throws},
 * and caught at a higher level.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 * @see IOException
 */
public class Main {

    /**
     * Calls {@link #methodA} and handles any {@link IOException} using a {@code try} and {@code catch} block.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            methodA();
        } catch (IOException e) {
            System.out.println("Exception caught in main: " + e.getMessage());
        }
    }

    /**
     * Calls {@link #methodB} and propagates the exception using {@code throws}.
     *
     * @throws IOException if an error occurs in {@link #methodB}
     */
    public static void methodA() throws IOException {
        methodB();
    }

    /**
     * Calls {@link #methodC} and propagates the exception using {@code throws}.
     *
     * @throws IOException if an error occurs in {@link #methodC}
     */
    public static void methodB() throws IOException {
        methodC();
    }

    /**
     * Throws an {@link IOException} to simulate an error that is not handled here.
     *
     * @throws IOException always thrown to demonstrate propagation
     */
    public static void methodC() throws IOException {
        throw new IOException("Simulated file access error.");
    }
}
