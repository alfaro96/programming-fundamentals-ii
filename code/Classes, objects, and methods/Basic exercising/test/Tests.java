import org.junit.Test;
import static org.junit.Assert.*;
import es.uclm.esii.math.complex.ComplexNumber;

/**
 * This class performs unit testing on the {@link ComplexNumber} class to verify
 * the correctness of constructors, arithmetic operations, and string representation
 * as defined in the project requirements.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see ComplexNumber
 */
public class Tests {

    /**
     * Verifies that the <b>default constructor</b> initializes both parts to zero.
     * <p>
     * <b>Requirement:</b> "Default constructor: Starts both fields to {@code 0}."
     * </p>
     */
    @Test
    public void testDefaultConstructor() {
        ComplexNumber zero = new ComplexNumber();
        ComplexNumber expected = new ComplexNumber(0.0, 0.0);

        assertTrue("Default constructor should initialize to 0 + 0i", zero.equals(expected));
    }

    /**
     * Verifies the <b>integer constructor</b>.
     * <p>
     * <b>Requirement:</b> "Integer constructor: Takes two integer values."
     * </p>
     * Checks if integers are correctly promoted to internal {@code double} representation.
     */
    @Test
    public void testIntegerConstructor() {
        ComplexNumber num = new ComplexNumber(3, 4);

        // We verify against a double constructor to ensure internal representation is correct
        ComplexNumber expected = new ComplexNumber(3.0, 4.0);

        assertTrue("Integer constructor should correctly set values as doubles", num.equals(expected));
    }

    /**
     * Verifies the <b>copy constructor</b> logic.
     * <p>
     * <b>Requirement:</b> "Copy constructor: Takes a different {@link ComplexNumber} object and copies its values."
     * </p>
     * Ensures that the new object contains the same values but is a distinct instance (deep copy).
     */
    @Test
    public void testCopyConstructor() {
        ComplexNumber original = new ComplexNumber(1.5, -2.5);
        ComplexNumber copy = new ComplexNumber(original);

        assertTrue("Copy should be mathematically equal to original", copy.equals(original));
        assertNotSame("Copy should be a new object reference, not an alias", original, copy);
    }

    /**
     * Verifies the <b>addition</b> operation logic.
     * <p>
     * <b>Requirement:</b> {@link ComplexNumber#add} adds a complex number and returns a new one.
     * </p>
     * <p><b>Math Logic:</b> {@code (1 + 2i) + (3 + 4i) = 4 + 6i}</p>
     */
    @Test
    public void testAddOperation() {
        ComplexNumber c1 = new ComplexNumber(1.0, 2.0);
        ComplexNumber c2 = new ComplexNumber(3.0, 4.0);
        ComplexNumber expected = new ComplexNumber(4.0, 6.0);

        ComplexNumber result = c1.add(c2);

        assertTrue("Addition result should be 4 + 6i", result.equals(expected));
    }

    /**
     * Verifies the <b>subtraction</b> operation logic.
     * <p>
     * <b>Requirement:</b> {@link ComplexNumber#subtract} subtracts a complex number and returns a new one.
     * </p>
     * <p><b>Math Logic:</b> {@code (5 + 6i) - (2 + 3i) = 3 + 3i}</p>
     */
    @Test
    public void testSubtractOperation() {
        ComplexNumber c1 = new ComplexNumber(5.0, 6.0);
        ComplexNumber c2 = new ComplexNumber(2.0, 3.0);
        ComplexNumber expected = new ComplexNumber(3.0, 3.0);

        ComplexNumber result = c1.subtract(c2);

        assertTrue("Subtraction result should be 3 + 3i", result.equals(expected));
    }

    /**
     * Verifies the <b>scalar multiplication</b> logic.
     * <p>
     * <b>Requirement:</b> {@link ComplexNumber#multiply(double)} accepts a scalar value.
     * </p>
     * <p><b>Math Logic:</b> {@code (2 + 3i) * 2 = 4 + 6i}</p>
     */
    @Test
    public void testScalarMultiplyOperation() {
        ComplexNumber c1 = new ComplexNumber(2.0, 3.0);
        double scalar = 2.0;
        ComplexNumber expected = new ComplexNumber(4.0, 6.0);

        ComplexNumber result = c1.multiply(scalar);

        assertTrue("Scalar multiplication result should be 4 + 6i", result.equals(expected));
    }

    /**
     * Verifies the <b>complex multiplication</b> logic.
     * <p>
     * <b>Requirement:</b> {@link ComplexNumber#multiply(ComplexNumber)} accepts another complex number.
     * </p>
     * <p><b>Math Logic:</b> {@code (a + bi)(c + di) = (ac - bd) + (ad + bc)i}</p>
     * <p>Example: {@code (1 + 2i)(3 + 4i) = (3 - 8) + (4 + 6)i = -5 + 10i}</p>
     */
    @Test
    public void testComplexMultiplyOperation() {
        ComplexNumber c1 = new ComplexNumber(1.0, 2.0);
        ComplexNumber c2 = new ComplexNumber(3.0, 4.0);
        ComplexNumber expected = new ComplexNumber(-5.0, 10.0);

        ComplexNumber result = c1.multiply(c2);

        assertTrue("Complex multiplication result should be -5 + 10i", result.equals(expected));
    }

    /**
     * Verifies the <b>string representation</b> format.
     * <p>
     * <b>Requirement:</b> {@link ComplexNumber#toString} returns "human-legible text representation".
     * </p>
     * <p><b>Format:</b> {@code "real + imaginaryi"} (e.g., {@code "3.50 + 2.00i"}).</p>
     */
    @Test
    public void testToStringFormat() {
        ComplexNumber num = new ComplexNumber(3.5, 2.0);
        // The implementation uses String.format("%.2f...")
        String expected = "3.50 + 2.00i";

        assertEquals("String representation must match standard format '%.2f + %.2fi'",
                expected, num.toString());
    }

    /**
     * Verifies the <b>epsilon comparison</b> logic in {@link ComplexNumber#equals)}.
     * <p>
     * <b>Requirement:</b> Compares two complex numbers using an epsilon comparison to handle rounding errors.
     * </p>
     * Checks if very small differences (smaller than {@code 1e-9}) are ignored.
     */
    @Test
    public void testEqualsWithEpsilon() {
        ComplexNumber standard = new ComplexNumber(1.0, 1.0);

        // Create a number that differs only by a tiny fraction (within epsilon)
        ComplexNumber tinyDiff = new ComplexNumber(1.0 + 1e-10, 1.0);
        assertTrue("Numbers within epsilon should be considered equal", standard.equals(tinyDiff));

        // Create a number that differs significantly
        ComplexNumber bigDiff = new ComplexNumber(1.1, 1.0);
        assertFalse("Numbers with significant difference should NOT be equal", standard.equals(bigDiff));
    }
}
