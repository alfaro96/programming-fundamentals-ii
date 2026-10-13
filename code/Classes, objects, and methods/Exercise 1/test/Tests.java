import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link Monomial} class: its constructor, its written form and its operations,
 * which must return new monomials and leave the original ones unchanged.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see Monomial
 */
public class Tests {

    /**
     * The largest difference allowed when comparing two {@code double} values.
     */
    private static final double DELTA = 1e-9;

    /**
     * Checks that the constructor stores the coefficient and the exponent.
     */
    @Test
    public void testConstructor() {
        Monomial m = new Monomial(2.0, 3);

        assertEquals("The coefficient should be 2.0", 2.0, m.coefficient, DELTA);
        assertEquals("The exponent should be 3", 3, m.exponent);
    }

    /**
     * Checks {@link Monomial#describe}: 2<i>X</i><sup>3</sup> is written {@code "2.0X^3"}, and a
     * negative coefficient keeps its sign.
     */
    @Test
    public void testDescribe() {
        assertEquals("2X^3 should be written 2.0X^3", "2.0X^3", new Monomial(2.0, 3).describe());
        assertEquals("-1.5X^-2 should be written -1.5X^-2", "-1.5X^-2", new Monomial(-1.5, -2).describe());
    }

    /**
     * Checks {@link Monomial#addition} with the same exponent:
     * 2<i>X</i><sup>3</sup> + 3<i>X</i><sup>3</sup> = 5<i>X</i><sup>3</sup>.
     */
    @Test
    public void testAdditionSameExponent() {
        Monomial result = new Monomial(2.0, 3).addition(new Monomial(3.0, 3));

        assertNotNull("The sum of two monomials with the same exponent should not be null", result);
        assertEquals("The coefficient should be 5.0", 5.0, result.coefficient, DELTA);
        assertEquals("The exponent should be 3", 3, result.exponent);
    }

    /**
     * Checks {@link Monomial#addition} with different exponents: 2<i>X</i><sup>3</sup> +
     * 3<i>X</i><sup>2</sup> is not a monomial, so the result is {@code null}.
     */
    @Test
    public void testAdditionDifferentExponents() {
        assertNull("The sum of monomials with different exponents should be null",
                new Monomial(2.0, 3).addition(new Monomial(3.0, 2)));
    }

    /**
     * Checks {@link Monomial#scalarProduct}: 2<i>X</i><sup>3</sup> &middot; 4 = 8<i>X</i><sup>3</sup>.
     */
    @Test
    public void testScalarProduct() {
        Monomial result = new Monomial(2.0, 3).scalarProduct(4.0);

        assertEquals("The coefficient should be 8.0", 8.0, result.coefficient, DELTA);
        assertEquals("The exponent should be 3", 3, result.exponent);
    }

    /**
     * Checks {@link Monomial#product}:
     * 2<i>X</i><sup>3</sup> &middot; 3<i>X</i><sup>2</sup> = 6<i>X</i><sup>5</sup>.
     */
    @Test
    public void testProduct() {
        Monomial result = new Monomial(2.0, 3).product(new Monomial(3.0, 2));

        assertEquals("The coefficient should be 6.0", 6.0, result.coefficient, DELTA);
        assertEquals("The exponent should be 5", 5, result.exponent);
    }

    /**
     * Checks {@link Monomial#evaluate}: 2<i>X</i><sup>3</sup> at <i>X</i> = 2 is 16, and
     * <i>X</i><sup>-1</sup> at <i>X</i> = 4 is 0.25.
     */
    @Test
    public void testEvaluate() {
        assertEquals("2X^3 at X = 2 should be 16.0", 16.0, new Monomial(2.0, 3).evaluate(2.0), DELTA);
        assertEquals("X^-1 at X = 4 should be 0.25", 0.25, new Monomial(1.0, -1).evaluate(4.0), DELTA);
    }

    /**
     * Checks that the operations return new monomials and leave both operands unchanged.
     */
    @Test
    public void testOperandsUnchanged() {
        Monomial m1 = new Monomial(2.0, 3);
        Monomial m2 = new Monomial(3.0, 3);

        Monomial sum = m1.addition(m2);
        Monomial scaled = m1.scalarProduct(4.0);
        Monomial product = m1.product(m2);

        assertNotSame("addition should return a new monomial", m1, sum);
        assertNotSame("scalarProduct should return a new monomial", m1, scaled);
        assertNotSame("product should return a new monomial", m1, product);
        assertEquals("m1 should keep its coefficient", 2.0, m1.coefficient, DELTA);
        assertEquals("m1 should keep its exponent", 3, m1.exponent);
        assertEquals("m2 should keep its coefficient", 3.0, m2.coefficient, DELTA);
        assertEquals("m2 should keep its exponent", 3, m2.exponent);
    }
}
