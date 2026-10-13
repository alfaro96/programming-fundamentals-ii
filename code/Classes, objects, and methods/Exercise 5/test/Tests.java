import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link Polynomial} class: its three constructors, getting, adding and replacing
 * monomials, the operations between polynomials, evaluation and equality.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Polynomial
 * @see Monomial
 */
public class Tests {

    /**
     * The largest difference allowed when comparing two {@code double} values.
     */
    private static final double DELTA = 1e-9;

    /**
     * Checks that a polynomial has the expected coefficients: the one at position <i>i</i> is
     * that of <i>X</i><sup><i>i</i></sup>, and a coefficient of 0 means there must be no
     * monomial. Every exponent above the last coefficient must have no monomial either.
     *
     * @param expected the expected coefficients, from that of <i>X</i><sup>0</sup> upward.
     * @param p the polynomial to check.
     */
    private static void assertCoefficients(double[] expected, Polynomial p) {
        assertNotNull("The polynomial should not be null", p);
        for (int i = 0; i < expected.length; i++) {
            Monomial m = p.getMonomial(i);
            if (expected[i] == 0) {
                assertNull("A coefficient of 0 should leave no monomial with exponent " + i, m);
            } else {
                assertNotNull("There should be a monomial with exponent " + i, m);
                assertEquals("The coefficient of X^" + i + " should be " + expected[i], expected[i], m.coefficient, DELTA);
                assertEquals("The monomial at position " + i + " should have exponent " + i, i, m.exponent);
            }
        }
        for (int i = expected.length; i < p.maxSize; i++) {
            assertNull("There should be no monomial with exponent " + i, p.getMonomial(i));
        }
    }

    /**
     * Checks that the empty constructor creates an array of the given length with no
     * monomials.
     */
    @Test
    public void testEmptyConstructor() {
        Polynomial p = new Polynomial(5);

        assertEquals("maxSize should be 5", 5, p.maxSize);
        assertEquals("The array should have length 5", 5, p.monomials.length);
        assertEquals("numMonomials should be 0", 0, p.numMonomials);
        for (int i = 0; i < 5; i++) {
            assertNull("There should be no monomial with exponent " + i, p.getMonomial(i));
        }
    }

    /**
     * Checks the constructor from coefficients: {1, 0, 3} is 1 + 3<i>X</i><sup>2</sup>, with
     * no monomial for the coefficient of 0.
     */
    @Test
    public void testCoefficientsConstructor() {
        double[] coefficients = {1.0, 0.0, 3.0};
        Polynomial p = new Polynomial(coefficients);

        assertEquals("maxSize should be 3", 3, p.maxSize);
        assertEquals("numMonomials should be 2", 2, p.numMonomials);
        assertNull("There should be no monomial with exponent 1", p.getMonomial(1));
        assertCoefficients(new double[]{1.0, 0.0, 3.0}, p);
    }

    /**
     * Checks the constructor from monomials: each one goes to the position of its exponent,
     * whatever its position in the array.
     */
    @Test
    public void testMonomialsConstructor() {
        Monomial[] monomials = {new Monomial(5.0, 2), new Monomial(2.0, 0)};
        Polynomial p = new Polynomial(monomials);

        assertEquals("numMonomials should be 2", 2, p.numMonomials);
        assertCoefficients(new double[]{2.0, 0.0, 5.0}, p);
    }

    /**
     * Checks that {@link Polynomial#getMonomial} returns {@code null} for an exponent with no
     * monomial and for one outside the array.
     */
    @Test
    public void testGetMonomialMissing() {
        double[] coefficients = {4.0, 0.0, 7.0};
        Polynomial p = new Polynomial(coefficients);

        assertNull("There should be no monomial with exponent 1", p.getMonomial(1));
        assertNull("There should be no monomial with exponent 3", p.getMonomial(3));
        assertNull("There should be no monomial with exponent -1", p.getMonomial(-1));
    }

    /**
     * Checks that {@link Polynomial#addMonomial} stores a monomial at the position of its
     * exponent, adds it to the one already there, and ignores one the array has no room for.
     */
    @Test
    public void testAddMonomial() {
        Polynomial p = new Polynomial(3);

        p.addMonomial(new Monomial(3.0, 2));
        assertEquals("numMonomials should be 1", 1, p.numMonomials);
        assertCoefficients(new double[]{0.0, 0.0, 3.0}, p);

        p.addMonomial(new Monomial(4.0, 2));
        assertEquals("Adding to an existing monomial should not change numMonomials", 1, p.numMonomials);
        assertCoefficients(new double[]{0.0, 0.0, 7.0}, p);

        p.addMonomial(new Monomial(1.0, 3));
        assertEquals("A monomial with no room should not be added", 1, p.numMonomials);

        p.addMonomial(new Monomial(-7.0, 2));
        assertNull("A sum of 0 should leave the position empty", p.getMonomial(2));
        assertEquals("A sum of 0 should leave no monomials", 0, p.numMonomials);

        p.addMonomial(new Monomial(0.0, 1));
        assertNull("A monomial with coefficient 0 should not be stored", p.getMonomial(1));
        assertEquals("A monomial with coefficient 0 should not count", 0, p.numMonomials);
    }

    /**
     * Checks that {@link Polynomial#replaceMonomial} replaces the monomial with the same
     * exponent, removes it if the new coefficient is 0, and does nothing if there is none.
     */
    @Test
    public void testReplaceMonomial() {
        double[] coefficients = {1.0, 0.0, 3.0};
        Polynomial p = new Polynomial(coefficients);

        p.replaceMonomial(new Monomial(-2.0, 2));
        assertCoefficients(new double[]{1.0, 0.0, -2.0}, p);

        p.replaceMonomial(new Monomial(5.0, 1));
        assertNull("There should still be no monomial with exponent 1", p.getMonomial(1));
        assertEquals("numMonomials should still be 2", 2, p.numMonomials);

        p.replaceMonomial(new Monomial(0.0, 2));
        assertCoefficients(new double[]{1.0}, p);
        assertEquals("Replacing with a coefficient of 0 should remove the monomial", 1, p.numMonomials);
    }

    /**
     * Checks {@link Polynomial#add}: (1 + 2<i>X</i><sup>2</sup>) + (3 + 4<i>X</i> + 4<i>X</i><sup>2</sup> +
     * <i>X</i><sup>3</sup>) = 4 + 4<i>X</i> + 6<i>X</i><sup>2</sup> + <i>X</i><sup>3</sup>, and both
     * operands stay unchanged.
     */
    @Test
    public void testAdd() {
        double[] c1 = {1.0, 0.0, 2.0};
        double[] c2 = {3.0, 4.0, 4.0, 1.0};
        Polynomial p1 = new Polynomial(c1);
        Polynomial p2 = new Polynomial(c2);

        assertCoefficients(new double[]{4.0, 4.0, 6.0, 1.0}, p1.add(p2));
        assertCoefficients(new double[]{1.0, 0.0, 2.0}, p1);
        assertCoefficients(new double[]{3.0, 4.0, 4.0, 1.0}, p2);
    }

    /**
     * Checks that adding a polynomial and its opposite leaves no monomials: the coefficients
     * that cancel out are not kept as monomials with coefficient 0.
     */
    @Test
    public void testAddCancelsOut() {
        double[] coefficients = {1.0, 0.0, 3.0};
        Polynomial p = new Polynomial(coefficients);
        Polynomial sum = p.add(p.scalarProduct(-1.0));

        assertEquals("p + (-p) should have no monomials", 0, sum.numMonomials);
        assertEquals("p + (-p) should be described as 0", "0", sum.describe());
    }

    /**
     * Checks {@link Polynomial#scalarProduct}: (2 + 3<i>X</i>) &middot; 2 = 4 + 6<i>X</i>, and the
     * polynomial stays unchanged.
     */
    @Test
    public void testScalarProduct() {
        double[] coefficients = {2.0, 3.0};
        Polynomial p = new Polynomial(coefficients);

        assertCoefficients(new double[]{4.0, 6.0}, p.scalarProduct(2.0));
        assertCoefficients(new double[]{2.0, 3.0}, p);
    }

    /**
     * Checks {@link Polynomial#product}: (1 + <i>X</i>)(2 &minus; <i>X</i> + <i>X</i><sup>2</sup>) =
     * 2 + <i>X</i> + 0<i>X</i><sup>2</sup> + <i>X</i><sup>3</sup>, and both operands stay unchanged.
     */
    @Test
    public void testProduct() {
        double[] c1 = {1.0, 1.0};
        double[] c2 = {2.0, -1.0, 1.0};
        Polynomial p1 = new Polynomial(c1);
        Polynomial p2 = new Polynomial(c2);

        assertCoefficients(new double[]{2.0, 1.0, 0.0, 1.0}, p1.product(p2));
        assertCoefficients(new double[]{1.0, 1.0}, p1);
        assertCoefficients(new double[]{2.0, -1.0, 1.0}, p2);
    }

    /**
     * Checks {@link Polynomial#solve}: 2 + 3<i>X</i> at <i>X</i> = 2 is 8, and
     * 1 &minus; <i>X</i><sup>2</sup> + <i>X</i><sup>3</sup> at <i>X</i> = &minus;2 is &minus;11.
     */
    @Test
    public void testSolve() {
        double[] c1 = {2.0, 3.0};
        double[] c2 = {1.0, 0.0, -1.0, 1.0};

        assertEquals("2 + 3X at X = 2 should be 8.0", 8.0, new Polynomial(c1).solve(2.0), DELTA);
        assertEquals("1 - X^2 + X^3 at X = -2 should be -11.0", -11.0, new Polynomial(c2).solve(-2.0), DELTA);
    }

    /**
     * Checks that {@link Polynomial#describe} includes every monomial, and gives {@code "0"}
     * for a polynomial with none.
     */
    @Test
    public void testDescribe() {
        double[] coefficients = {1.0, 0.0, 3.0};
        String text = new Polynomial(coefficients).describe();

        assertTrue("It should include the monomial 1.0X^0", text.contains("1.0X^0"));
        assertTrue("It should include the monomial 3.0X^2", text.contains("3.0X^2"));
        assertEquals("A polynomial with no monomials should be 0", "0", new Polynomial(3).describe());
    }

    /**
     * Checks {@link Polynomial#isEqualTo}: polynomials with the same coefficients are equal, even
     * if built in different ways or with arrays of different lengths, and polynomials that
     * differ in one coefficient are not.
     */
    @Test
    public void testIsEqualTo() {
        double[] c1 = {1.0, 2.0, 3.0};
        double[] c2 = {1.0, 2.0, 9.0};
        Monomial[] monomials = {new Monomial(3.0, 2), new Monomial(2.0, 1), new Monomial(1.0, 0)};
        Polynomial p1 = new Polynomial(c1);
        Polynomial longer = new Polynomial(6);
        longer.addMonomial(new Monomial(1.0, 0));
        longer.addMonomial(new Monomial(2.0, 1));
        longer.addMonomial(new Monomial(3.0, 2));

        assertTrue("The same coefficients should be equal", p1.isEqualTo(new Polynomial(c1)));
        assertTrue("Built from monomials, it should be equal", p1.isEqualTo(new Polynomial(monomials)));
        assertTrue("With a longer array, it should be equal", p1.isEqualTo(longer));
        assertFalse("A different coefficient should not be equal", p1.isEqualTo(new Polynomial(c2)));
    }
}
