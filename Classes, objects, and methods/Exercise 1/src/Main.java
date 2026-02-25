/**
 * Main application class to demonstrate the {@link Monomial} class.
 * Creates several monomials and exercises all their operations.
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Main {

    /**
     * Entry point of the program.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Constructor and toString()
        Monomial m1 = new Monomial(2.0, 3);
        System.out.println("--- Monomial constructor and toString() ---");
        System.out.println("Created: Monomial(2.0, 3)");
        System.out.println("toString(): " + m1);
        System.out.println();

        // 2. addition() with equal exponents (valid)
        Monomial m2 = new Monomial(3.0, 3);
        Monomial addValid = m1.addition(m2);
        System.out.println("--- addition() with equal exponents ---");
        System.out.println("m1: " + m1);
        System.out.println("m2: " + m2);
        System.out.println("m1.addition(m2): " + addValid);
        System.out.println("Coefficient (expected 5.0): " + addValid.coefficient);
        System.out.println("Exponent (expected 3):   " + addValid.exponent);
        System.out.println();

        // 3. addition() with different exponents (invalid -> null)
        Monomial m3 = new Monomial(3.0, 2);
        Monomial addInvalid = m1.addition(m3);
        System.out.println("--- addition() with different exponents ---");
        System.out.println("m1: " + m1);
        System.out.println("m3 (exp 2): " + m3);
        System.out.println("m1.addition(m3) (expected null): " + addInvalid);
        System.out.println();

        // 4. scalarProduct()
        Monomial scalar = m1.scalarProduct(4.0);
        System.out.println("--- scalarProduct() ---");
        System.out.println("m1: " + m1);
        System.out.println("m1.scalarProduct(4.0): " + scalar);
        System.out.println("Coefficient (expected 8.0): " + scalar.coefficient);
        System.out.println("Exponent (expected 3):   " + scalar.exponent);
        System.out.println();

        // 5. product() of two monomials
        Monomial product = m1.product(m3);
        System.out.println("--- product() ---");
        System.out.println("m1: " + m1);
        System.out.println("m3: " + m3);
        System.out.println("m1.product(m3): " + product);
        System.out.println("Coefficient (expected 6.0): " + product.coefficient);
        System.out.println("Exponent (expected 5):   " + product.exponent);
        System.out.println();

        // 6. evaluate()
        double result = m1.evaluate(2.0);
        System.out.println("--- evaluate() ---");
        System.out.println("m1: " + m1);
        System.out.println("m1.evaluate(2.0) (expected 16.0): " + result);
        System.out.println();
    }
}
