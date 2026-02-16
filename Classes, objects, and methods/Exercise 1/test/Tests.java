import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Laboratory Assignment: Monomial Test Suite.
 * <p>
 * This class performs unit testing on the {@link Monomial} class to verify
 * the correctness of algebraic operations, string representation, and evaluation logic.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Monomial
 */
public class Tests {

    /**
     * Delta value for floating-point comparisons ({@code double}).
     * Essential for comparing coefficients and evaluation results to avoid rounding errors.
     */
    private static final double DELTA = 1e-9;

    /**
     * Verifies the constructor and the {@link Monomial#toString()} representation.
     * <p>
     * <b>Requirement:</b> "toString(): Returns a String containing the written representation... e.g., {@code "2X^3"}".
     * </p>
     * <p>
     * <b>Note:</b> Since the coefficient is a {@code double}, Java defaults to formatting integer values with a decimal point
     * (e.g., input {@code 2} becomes {@code "2.0"}).
     * </p>
     */
    @Test
    public void testConstructorAndToString() {
        Monomial m = new Monomial(2.0, 3);

        // Expected format based on string concatenation in source: "2.0X^3"
        String expected = "2.0X^3";

        assertEquals("String representation should match 'coeffX^exp' format",
                expected, m.toString());
    }

    /**
     * Verifies the {@link Monomial#addition(Monomial)} operation with <b>equal exponents</b>.
     * <p>
     * <b>Requirement:</b> Returns a new {@link Monomial} representing the sum.
     * </p>
     * <p><b>Math Logic:</b> {@code 2x^3 + 3x^3 = 5x^3}</p>
     */
    @Test
    public void testAdditionValid() {
        Monomial m1 = new Monomial(2.0, 3);
        Monomial m2 = new Monomial(3.0, 3);

        Monomial result = m1.addition(m2);

        assertNotNull("Result should not be null for equal exponents", result);
        assertEquals("Coefficient should be sum of coefficients (2+3=5)",
                5.0, result.coefficient, DELTA);
        assertEquals("Exponent should remain unchanged",
                3, result.exponent);
    }

    /**
     * Verifies the {@link Monomial#addition(Monomial)} operation with <b>different exponents</b>.
     * <p>
     * <b>Implementation Note:</b> The provided method returns {@code null} if exponents differ,
     * as they cannot be combined into a single monomial term.
     * </p>
     * <p><b>Math Logic:</b> {@code 2x^3 + 3x^2} cannot be simplified.</p>
     */
    @Test
    public void testAdditionInvalid() {
        Monomial m1 = new Monomial(2.0, 3);
        Monomial m2 = new Monomial(3.0, 2); // Different exponent

        Monomial result = m1.addition(m2);

        assertNull("Result should be null when exponents differ", result);
    }

    /**
     * Verifies the {@link Monomial#scalarProduct} operation.
     * <p>
     * <b>Requirement:</b> Returns a new {@link Monomial} representing the multiplication by a scalar.
     * </p>
     * <p><b>Math Logic:</b> {@code (2x^3) * 4 = 8x^3}</p>
     */
    @Test
    public void testScalarProduct() {
        Monomial m = new Monomial(2.0, 3);
        double scalar = 4.0;

        Monomial result = m.scalarProduct(scalar);

        assertEquals("Coefficient should be multiplied by scalar (2*4=8)",
                8.0, result.coefficient, DELTA);
        assertEquals("Exponent should remain unchanged",
                3, result.exponent);
    }

    /**
     * Verifies the {@link Monomial#product} operation.
     * <p>
     * <b>Requirement:</b> Returns a new {@link Monomial} representing the multiplication of two monomials.
     * </p>
     * <p><b>Math Logic:</b> {@code (2x^3) * (3x^2) = (2*3)x^(3+2) = 6x^5}</p>
     */
    @Test
    public void testMonomialProduct() {
        Monomial m1 = new Monomial(2.0, 3);
        Monomial m2 = new Monomial(3.0, 2);

        Monomial result = m1.product(m2);

        assertEquals("Coefficients should be multiplied (2*3=6)",
                6.0, result.coefficient, DELTA);
        assertEquals("Exponents should be added (3+2=5)",
                5, result.exponent);
    }

    /**
     * Verifies the {@link Monomial#evaluate(double)} logic.
     * <p>
     * <b>Requirement:</b> Returns the numerical result of solving the monomial for a given value.
     * </p>
     * <p><b>Math Logic:</b> For {@code 2x^3} with {@code x=2}: {@code 2 * (2^3) = 2 * 8 = 16}.</p>
     */
    @Test
    public void testEvaluate() {
        Monomial m = new Monomial(2.0, 3);
        double xValue = 2.0;

        double result = m.evaluate(xValue);

        assertEquals("Evaluation result for 2(2)^3 should be 16.0",
                16.0, result, DELTA);
    }
}
