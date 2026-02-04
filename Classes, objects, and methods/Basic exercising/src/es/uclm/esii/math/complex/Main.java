package es.uclm.esii.math.complex;

/**
 * Testing class for the {@link ComplexNumber} implementation.
 * This class serves as a verification environment to ensure all constructors,
 * mathematical operations, and object methods behave as expected according
 * to the laboratory requirements.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Main entry point of the testing application.
     * This method executes an incremental testing strategy, verifying
     * constructors, mathematical operations (addition, subtraction,
     * and multiplication), and object comparisons using epsilon logic.
     *
     * @param args Command line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        // Step 1: Test constructors
        ComplexNumber c1 = new ComplexNumber(); // Default
        ComplexNumber c2 = new ComplexNumber(3, 2); // Integer
        ComplexNumber c3 = new ComplexNumber(5.5, -1.2); // Double
        ComplexNumber c4 = new ComplexNumber(c2); // Copy

        System.out.println("--- Testing toString() and constructors ---");
        System.out.println("c1 (default): " + c1);
        System.out.println("c2 (integer): " + c2);
        System.out.println("c3 (double): " + c3);
        System.out.println("c4 (copy of c2): " + c4);

        // Step 2: Test mathematical methods
        System.out.println("\n--- Testing mathematical operations ---");
        ComplexNumber sum = c2.add(c3);
        ComplexNumber sub = c3.subtract(c2);
        ComplexNumber multScalar = c2.multiply(2.0);
        ComplexNumber multComp = c2.multiply(c3);

        System.out.println(c2 + " + " + c3 + " = " + sum);
        System.out.println(c3 + " - " + c2 + " = " + sub);
        System.out.println(c2 + " * 2.0 = " + multScalar);
        System.out.println(c2 + " * " + c3 + " = " + multComp);

        // Step 3: Test equality
        System.out.println("\n--- Testing equals() ---");
        System.out.println("Is c2 equal to c4? " + c2.equals(c4)); // Expected: true
        System.out.println("Is c2 equal to c3? " + c2.equals(c3)); // Expected: false
    }
}
