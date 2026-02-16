import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class performs unit testing on the {@link Polynomial} class to verify
 * the correctness of constructors, monomial management, algebraic operations
 * (addition, scalar product, multiplication), and evaluation logic.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Polynomial
 * @see Monomial
 */
public class Tests {

    /**
     * Delta value for floating-point comparisons ({@code double}).
     * Essential for verifying {@link Polynomial#solve} and coefficient equality.
     */
    private static final double DELTA = 1e-9;

    /**
     * Verifies the <b>empty constructor</b> with capacity.
     * <p>
     * <b>Requirement:</b> "A constructor that creates an empty polynomial with a specified maximum capacity."
     * </p>
     */
    @Test
    public void testEmptyConstructor() {
        int capacity = 5;
        Polynomial p = new Polynomial(capacity);

        assertNotNull("Polynomial object should be created", p);
        assertEquals("NumMonomials should be 0", 0, p.numMonomials);
        assertEquals("MaxSize should match capacity", capacity, p.maxSize);
        assertNotNull("Monomials array should be initialized", p.monomials);
    }

    /**
     * Verifies the <b>coefficient array constructor</b>.
     * <p>
     * <b>Requirement:</b> "A constructor that takes an array of real numbers... index represents the exponent."
     * </p>
     * <p><b>Logic:</b> Input {@code [1.0, 0.0, 2.0]} corresponds to $ 1.0x^0 + 2.0x^2 $.</p>
     */
    @Test
    public void testCoefficientsConstructor() {
        double[] coeffs = {1.0, 0.0, 2.5}; // 1 + 2.5x^2
        Polynomial p = new Polynomial(coeffs);

        assertEquals("MaxSize should match coefficient array length", 3, p.maxSize);
        assertEquals("Should count only non-zero coefficients", 2, p.numMonomials);

        // Verify monomials are created at correct indices
        assertNotNull("Monomial at index 0 should exist", p.monomials[0]);
        assertEquals("Coef at index 0 should be 1.0", 1.0, p.monomials[0].coefficient, DELTA);

        assertNull("Monomial at index 1 should be null (coeff was 0)", p.monomials[1]);

        assertNotNull("Monomial at index 2 should exist", p.monomials[2]);
        assertEquals("Coef at index 2 should be 2.5", 2.5, p.monomials[2].coefficient, DELTA);
    }

    /**
     * Verifies the <b>{@link Monomial} array constructor</b>.
     * <p>
     * <b>Requirement:</b> "A constructor that takes an array of {@link Monomial} objects."
     * </p>
     */
    @Test
    public void testMonomialArrayConstructor() {
        Monomial[] mons = new Monomial[3];
        mons[0] = new Monomial(3.0, 0);
        mons[2] = new Monomial(4.0, 2);

        Polynomial p = new Polynomial(mons);

        assertEquals("Should count non-null monomials", 2, p.numMonomials);
        assertEquals("Coefficient at pos 0 should be 3.0", 3.0, p.monomials[0].coefficient, DELTA);
    }

    /**
     * Verifies {@link Polynomial#addMonomial}.
     * <p>
     * <b>Requirement:</b> "Adds a monomial... at the correct position based on its exponent."
     * </p>
     * Checks that the monomial is placed in the array index corresponding to its exponent.
     */
    @Test
    public void testAddMonomial() {
        Polynomial p = new Polynomial(5);
        Monomial m = new Monomial(5.0, 2); // 5x^2

        p.addMonomial(m);

        assertEquals("NumMonomials should increment", 1, p.numMonomials);
        assertNotNull("Monomial should be stored at index 2", p.monomials[2]);
        assertEquals("Stored monomial should have coefficient 5.0", 5.0, p.monomials[2].coefficient, DELTA);
    }

    /**
     * Verifies {@link Polynomial#add}.
     * <p>
     * <b>Requirement:</b> "Adds another polynomial... and returns a <b>new</b> {@link Polynomial}."
     * </p>
     * <p><b>Math logic:</b> $ (1 + x) + (2x + x^2) = 1 + 3x + x^2 $</p>
     */
    @Test
    public void testPolynomialAddition() {
        Polynomial p1 = new Polynomial(new double[]{1.0, 1.0}); // 1 + x
        Polynomial p2 = new Polynomial(new double[]{0.0, 2.0, 1.0});  // 2x + x^2

        Polynomial result = p1.add(p2);

        assertNotNull("Result should not be null", result);
        assertNotSame("Result should be a new instance", p1, result);

        // Expected: 1 + 3x + x^2
        assertEquals("Const term (exp 0) should be 1.0", 1.0, result.getMonomial(0).coefficient, DELTA);
        assertEquals("Linear term (exp 1) should be 3.0", 3.0, result.getMonomial(1).coefficient, DELTA);
        assertEquals("Quad term (exp 2) should be 1.0", 1.0, result.getMonomial(2).coefficient, DELTA);
    }

    /**
     * Verifies {@link Polynomial#scalarProduct}.
     * <p>
     * <b>Requirement:</b> "Multiplies the polynomial by a scalar value and returns a <b>new</b> {@link Polynomial}."
     * </p>
     * <p><b>Math logic:</b> $ (2 + x^2) * 3 = 6 + 3x^2 $</p>
     */
    @Test
    public void testScalarProduct() {
        Polynomial p = new Polynomial(new double[]{2.0, 0.0, 1.0}); // 2 + x^2
        double scalar = 3.0;

        Polynomial result = p.scalarProduct(scalar);

        assertEquals("Const term should be 2 * 3 = 6", 6.0, result.getMonomial(0).coefficient, DELTA);
        assertEquals("Quad term should be 1 * 3 = 3", 3.0, result.getMonomial(2).coefficient, DELTA);
    }

    /**
     * Verifies {@link Polynomial#product(Polynomial)}.
     * <p>
     * <b>Requirement:</b> "Multiplies the current polynomial by another... returns a <b>new</b> {@link Polynomial}."
     * </p>
     * <p><b>Math logic:</b> $ (1 + x) * (x) = x + x^2 $</p>
     */
    @Test
    public void testPolynomialProduct() {
        Polynomial p1 = new Polynomial(new double[]{1.0, 1.0}); // 1 + x
        Polynomial p2 = new Polynomial(new double[]{0.0, 1.0}); // x

        Polynomial result = p1.product(p2);

        // Expected: x + x^2 (coeffs: 0, 1, 1)
        assertNull("Exp 0 should be null/0", result.getMonomial(0));
        assertEquals("Exp 1 should be 1.0", 1.0, result.getMonomial(1).coefficient, DELTA);
        assertEquals("Exp 2 should be 1.0", 1.0, result.getMonomial(2).coefficient, DELTA);
    }

    /**
     * Verifies {@link Polynomial#solve}.
     * <p>
     * <b>Requirement:</b> "Calculates and returns the numerical result of the polynomial."
     * </p>
     * <p><b>Math logic:</b> $ P(x) = 1 + 2x + x^2 $. For $ x = 2 $: $ 1 + 4 + 4 = 9 $.</p>
     */
    @Test
    public void testSolve() {
        Polynomial p = new Polynomial(new double[]{1.0, 2.0, 1.0}); // 1 + 2x + x^2
        double xValue = 2.0;

        double result = p.solve(xValue);

        assertEquals("P(2) for 1 + 2x + x^2 should be 9.0", 9.0, result, DELTA);
    }

    /**
     * Verifies {@link Polynomial#toString()}.
     * <p>
     * <b>Requirement:</b> "Returns a human-legible String representation."
     * </p>
     */
    @Test
    public void testToString() {
        // Logic based on provided Polynomial.java implementation
        Polynomial p = new Polynomial(new double[]{2.0, 3.0}); // 2 + 3x

        String result = p.toString();

        // Depending on implementation, checking for containment of terms
        assertTrue("String should contain '2.0X^0'", result.contains("2.0X^0"));
        assertTrue("String should contain '3.0X^1'", result.contains("3.0X^1"));
        assertTrue("String should contain separator ' + '", result.contains(" + "));
    }

    /**
     * Verifies {@link Polynomial#equals}.
     * <p>
     * <b>Requirement:</b> "Checks if two polynomials are equal by verifying if all their respective monomials are equal."
     * </p>
     */
    @Test
    public void testEquals() {
        Polynomial p1 = new Polynomial(new double[]{1.0, 2.0});
        Polynomial p2 = new Polynomial(new double[]{1.0, 2.0});
        Polynomial p3 = new Polynomial(new double[]{1.0, 3.0});

        assertTrue("Identical polynomials should be equal", p1.equals(p2));
        assertFalse("Different polynomials should not be equal", p1.equals(p3));
    }
}