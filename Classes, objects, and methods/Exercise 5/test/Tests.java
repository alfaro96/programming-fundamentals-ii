import org.junit.Test;
import static org.junit.Assert.*;

/**
 * {@link Polynomial} test suite.
 * <p>
 * This class performs unit testing on the {@link Polynomial} class to verify the
 * correctness of construction, monomial management, algebraic operations, evaluation,
 * and equality checks.
 * </p>
 * <p>
 * A polynomial is modeled as an array of {@link Monomial} objects where each
 * monomial's exponent corresponds to its index in the array:
 * {@code P(x) = c0 + c1·x + c2·x² + … + cₙ·xⁿ}.
 * </p>
 * <p><b>Covered scenarios:</b></p>
 * <ul>
 * <li>Empty constructor (capacity only).</li>
 * <li>Coefficient-array constructor ({@code double[]} → monomials by index).</li>
 * <li>Monomial-array constructor ({@code Monomial[]} → direct assignment).</li>
 * <li>{@link Polynomial#getMonomial(int)} – retrieves the monomial at a given exponent.</li>
 * <li>{@link Polynomial#addMonomial(Monomial)} – inserts a monomial at the correct position.</li>
 * <li>{@link Polynomial#add(Polynomial)} – polynomial addition.</li>
 * <li>{@link Polynomial#scalarProduct(double)} – polynomial × scalar.</li>
 * <li>{@link Polynomial#product(Polynomial)} – polynomial × polynomial.</li>
 * <li>{@link Polynomial#solve(double)} – numerical evaluation for a given {@code x}.</li>
 * <li>{@link Polynomial#equals(Object)} – {@code true} when all respective monomials are equal.</li>
 * </ul>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Polynomial
 * @see Monomial
 */
public class Tests {

    /** Delta value for floating-point comparisons. */
    private static final double DELTA = 1e-9;

    /**
     * Verifies the empty constructor creates a polynomial with the specified maximum
     * capacity and zero monomials.
     * <p>
     * <b>Requirement:</b> {@code numMonomials} must be {@code 0} and {@code maxSize}
     * must match the provided capacity.
     * </p>
     */
    @Test
    public void testEmptyConstructor() {
        Polynomial p = new Polynomial(5);

        assertEquals("maxSize must match the provided capacity", 5, p.maxSize);
        assertEquals("numMonomials must be 0 for empty polynomial", 0, p.numMonomials);
    }

    /**
     * Verifies the coefficient-array constructor.
     * <p>
     * <b>Scenario:</b> {@code new Polynomial(new double[]{1.0, 0.0, 3.0})} →
     * {@code P(x) = 1 + 0·x + 3x²}.
     * The monomial at index {@code 0} must have coefficient {@code 1.0}; the monomial
     * at index {@code 2} must have coefficient {@code 3.0}.
     * </p>
     */
    @Test
    public void testCoeffArrayConstructor() {
        double[] coeffs = {1.0, 0.0, 3.0};
        Polynomial p = new Polynomial(coeffs);

        assertNotNull("Polynomial must not be null", p);
        assertNotNull("Monomial at exponent 0 must exist", p.getMonomial(0));
        assertNotNull("Monomial at exponent 2 must exist", p.getMonomial(2));
        assertEquals("Coefficient at exponent 0 must be 1.0", 1.0, p.getMonomial(0).coefficient, DELTA);
        assertEquals("Coefficient at exponent 2 must be 3.0", 3.0, p.getMonomial(2).coefficient, DELTA);
    }

    /**
     * Verifies the {@link Monomial}-array constructor.
     * <p>
     * <b>Scenario:</b> Pass {@code [Monomial(2.0, 0), Monomial(5.0, 1)]} →
     * both monomials must be retrievable by exponent.
     * </p>
     */
    @Test
    public void testMonomialArrayConstructor() {
        Monomial[] monomials = {new Monomial(2.0, 0), new Monomial(5.0, 1)};
        Polynomial p = new Polynomial(monomials);

        assertNotNull("Polynomial must not be null", p);
        assertEquals("Coefficient at exponent 0 must be 2.0", 2.0, p.getMonomial(0).coefficient, DELTA);
        assertEquals("Coefficient at exponent 1 must be 5.0", 5.0, p.getMonomial(1).coefficient, DELTA);
    }

    /**
     * Verifies that {@link Polynomial#getMonomial(int)} returns the correct monomial
     * for a given exponent.
     */
    @Test
    public void testGetMonomial() {
        Polynomial p = new Polynomial(new double[]{4.0, 0.0, 7.0});

        assertNotNull("Monomial at exponent 0 must exist", p.getMonomial(0));
        assertNotNull("Monomial at exponent 2 must exist", p.getMonomial(2));
        assertEquals("Coefficient at exponent 0 must be 4.0", 4.0, p.getMonomial(0).coefficient, DELTA);
        assertEquals("Coefficient at exponent 2 must be 7.0", 7.0, p.getMonomial(2).coefficient, DELTA);
    }

    /**
     * Verifies that {@link Polynomial#addMonomial(Monomial)} places the monomial
     * at the position corresponding to its exponent and increments {@code numMonomials}.
     */
    @Test
    public void testAddMonomial() {
        Polynomial p = new Polynomial(5);
        p.addMonomial(new Monomial(3.0, 2));

        assertEquals("numMonomials must be 1 after one insertion", 1, p.numMonomials);
        assertNotNull("Monomial at exponent 2 must exist after insertion", p.getMonomial(2));
        assertEquals("Coefficient at exponent 2 must be 3.0", 3.0, p.getMonomial(2).coefficient, DELTA);
    }

    /**
     * Verifies {@link Polynomial#add(Polynomial)}.
     * <p>
     * <b>Math Logic:</b> {@code P1(x) = 1 + 2x²} and {@code P2(x) = 3 + 4x²} →
     * {@code P1 + P2 = 4 + 6x²}.
     * </p>
     */
    @Test
    public void testAdd() {
        Polynomial p1 = new Polynomial(new double[]{1.0, 0.0, 2.0});
        Polynomial p2 = new Polynomial(new double[]{3.0, 0.0, 4.0});

        Polynomial result = p1.add(p2);

        assertNotNull("add() result must not be null", result);
        assertEquals("Constant term (exp 0) must be 1 + 3=4", 4.0, result.getMonomial(0).coefficient, DELTA);
        assertEquals("Quadratic term (exp 2) must be 2 + 4=6", 6.0, result.getMonomial(2).coefficient, DELTA);
    }

    /**
     * Verifies {@link Polynomial#scalarProduct(double)}.
     * <p>
     * <b>Math Logic:</b> {@code P(x) = 2 + 3x}, scalar {@code 2} →
     * {@code 2·P(x) = 4 + 6x}.
     * </p>
     */
    @Test
    public void testScalarProduct() {
        Polynomial p = new Polynomial(new double[]{2.0, 3.0});
        Polynomial result = p.scalarProduct(2.0);

        assertNotNull("scalarProduct() result must not be null", result);
        assertEquals("Constant term (exp 0) must be 2*2=4", 4.0, result.getMonomial(0).coefficient, DELTA);
        assertEquals("Linear term (exp 1) must be 3*2=6", 6.0, result.getMonomial(1).coefficient, DELTA);
    }

    /**
     * Verifies {@link Polynomial#product(Polynomial)}.
     * <p>
     * <b>Math Logic:</b> {@code P1(x) = 1 + x} and {@code P2(x) = 1 + x} →
     * {@code P1·P2 = 1 + 2x + x²}.
     * </p>
     */
    @Test
    public void testProduct() {
        Polynomial p1 = new Polynomial(new double[]{1.0, 1.0});
        Polynomial p2 = new Polynomial(new double[]{1.0, 1.0});

        Polynomial result = p1.product(p2);

        assertNotNull("product() result must not be null", result);
        assertEquals("Constant term (exp 0) must be 1*1=1", 1.0, result.getMonomial(0).coefficient, DELTA);
        assertEquals("Linear term (exp 1) must be 1*1 + 1*1 = 2", 2.0, result.getMonomial(1).coefficient, DELTA);
        assertEquals("Quadratic term (exp 2) must be 1*1=1", 1.0, result.getMonomial(2).coefficient, DELTA);
    }

    /**
     * Verifies {@link Polynomial#solve(double)}.
     * <p>
     * <b>Math Logic:</b> {@code P(x) = 2 + 3x} with {@code x = 2}:
     * {@code 2 + 3·2 = 8}.
     * </p>
     */
    @Test
    public void testSolve() {
        Polynomial p = new Polynomial(new double[]{2.0, 3.0});
        double result = p.solve(2.0);

        assertEquals("P(2) = 2 + 3*2 = 8", 8.0, result, DELTA);
    }

    /**
     * Verifies {@link Polynomial#equals(Object)}.
     * <p>
     * Two polynomials with the same monomials at every position must be equal;
     * two polynomials that differ in at least one monomial must not.
     * </p>
     */
    @Test
    public void testEquals() {
        Polynomial p1 = new Polynomial(new double[]{1.0, 2.0, 3.0});
        Polynomial p2 = new Polynomial(new double[]{1.0, 2.0, 3.0});
        Polynomial p3 = new Polynomial(new double[]{1.0, 2.0, 9.0});

        assertTrue("Identical polynomials must be equal", p1.equals(p2));
        assertFalse("Polynomials differing at exp-2 must not be equal", p1.equals(p3));
    }
}