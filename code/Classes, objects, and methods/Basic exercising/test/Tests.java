import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the {@link ComplexNumber} class: its four constructors, the operations, which must
 * return new complex numbers, the description and equality with a tolerance.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see ComplexNumber
 */
public class Tests {

    /**
     * The largest difference allowed when comparing two {@code double} values.
     */
    private static final double DELTA = 1e-9;

    /**
     * Checks that a complex number has the expected parts.
     *
     * @param name the name of the complex number, for the failure messages.
     * @param real the expected real part.
     * @param imaginary the expected imaginary part.
     * @param c the complex number to check.
     */
    private static void assertComplex(String name, double real, double imaginary, ComplexNumber c) {
        assertNotNull(name + " should not be null", c);
        assertEquals("The real part of " + name + " should be " + real, real, c.real, DELTA);
        assertEquals("The imaginary part of " + name + " should be " + imaginary, imaginary, c.imaginary, DELTA);
    }

    /**
     * Checks that the default constructor creates 0 + 0<i>i</i>.
     */
    @Test
    public void testDefaultConstructor() {
        assertComplex("new ComplexNumber()", 0.0, 0.0, new ComplexNumber());
    }

    /**
     * Checks that the constructor with two integers stores them as the real and imaginary
     * parts.
     */
    @Test
    public void testIntegerConstructor() {
        assertComplex("new ComplexNumber(3, -4)", 3.0, -4.0, new ComplexNumber(3, -4));
    }

    /**
     * Checks that the constructor with two real numbers stores them as the real and imaginary
     * parts.
     */
    @Test
    public void testDoubleConstructor() {
        assertComplex("new ComplexNumber(1.5, -2.5)", 1.5, -2.5, new ComplexNumber(1.5, -2.5));
    }

    /**
     * Checks that the copy constructor creates a different object with the same parts, so
     * changing the copy does not change the original.
     */
    @Test
    public void testCopyConstructor() {
        ComplexNumber original = new ComplexNumber(1.5, -2.5);
        ComplexNumber copy = new ComplexNumber(original);

        assertNotSame("The copy should be a new object", original, copy);
        assertComplex("The copy", 1.5, -2.5, copy);
        copy.real = 9.0;
        assertComplex("The original", 1.5, -2.5, original);
    }

    /**
     * Checks {@link ComplexNumber#add}: (1 + 2<i>i</i>) + (3 + 4<i>i</i>) = 4 + 6<i>i</i>, and
     * both operands stay unchanged.
     */
    @Test
    public void testAdd() {
        ComplexNumber c1 = new ComplexNumber(1.0, 2.0);
        ComplexNumber c2 = new ComplexNumber(3.0, 4.0);

        assertComplex("The sum", 4.0, 6.0, c1.add(c2));
        assertComplex("c1", 1.0, 2.0, c1);
        assertComplex("c2", 3.0, 4.0, c2);
    }

    /**
     * Checks {@link ComplexNumber#subtract}: (5 + 6<i>i</i>) &minus; (2 + 8<i>i</i>) =
     * 3 &minus; 2<i>i</i>, and both operands stay unchanged.
     */
    @Test
    public void testSubtract() {
        ComplexNumber c1 = new ComplexNumber(5.0, 6.0);
        ComplexNumber c2 = new ComplexNumber(2.0, 8.0);

        assertComplex("The difference", 3.0, -2.0, c1.subtract(c2));
        assertComplex("c1", 5.0, 6.0, c1);
        assertComplex("c2", 2.0, 8.0, c2);
    }

    /**
     * Checks {@link ComplexNumber#multiply(double)}: (2 + 3<i>i</i>) &middot; 2 = 4 + 6<i>i</i>,
     * and the complex number stays unchanged.
     */
    @Test
    public void testMultiplyScalar() {
        ComplexNumber c = new ComplexNumber(2.0, 3.0);

        assertComplex("The product", 4.0, 6.0, c.multiply(2.0));
        assertComplex("c", 2.0, 3.0, c);
    }

    /**
     * Checks {@link ComplexNumber#multiply(ComplexNumber)}: (1 + 2<i>i</i>)(3 + 4<i>i</i>) =
     * &minus;5 + 10<i>i</i>, <i>i</i> &middot; <i>i</i> = &minus;1, and both operands stay unchanged.
     */
    @Test
    public void testMultiplyComplex() {
        ComplexNumber c1 = new ComplexNumber(1.0, 2.0);
        ComplexNumber c2 = new ComplexNumber(3.0, 4.0);
        ComplexNumber i = new ComplexNumber(0.0, 1.0);

        assertComplex("(1 + 2i)(3 + 4i)", -5.0, 10.0, c1.multiply(c2));
        assertComplex("i * i", -1.0, 0.0, i.multiply(i));
        assertComplex("c1", 1.0, 2.0, c1);
        assertComplex("c2", 3.0, 4.0, c2);
    }

    /**
     * Checks {@link ComplexNumber#describe} with the example of the statement, 3.5 + 2.0<i>i</i>,
     * and with a number created from integers.
     */
    @Test
    public void testDescribe() {
        assertEquals("3.5 + 2.0i should be described as 3.5 + 2.0i", "3.5 + 2.0i", new ComplexNumber(3.5, 2.0).describe());
        assertEquals("3 + 4i should be described as 3.0 + 4.0i", "3.0 + 4.0i", new ComplexNumber(3, 4).describe());
    }

    /**
     * Checks {@link ComplexNumber#isEqualTo}: numbers whose parts differ by less than the
     * tolerance are equal, and numbers that differ in either part are not.
     */
    @Test
    public void testIsEqualTo() {
        ComplexNumber c = new ComplexNumber(0.3, 1.0);

        assertTrue("Equal parts should be equal", c.isEqualTo(new ComplexNumber(0.3, 1.0)));
        assertTrue("0.1 + 0.2 and 0.3 should be equal", c.isEqualTo(new ComplexNumber(0.1 + 0.2, 1.0)));
        assertTrue("A difference of 1e-10 should be equal", c.isEqualTo(new ComplexNumber(0.3, 1.0 + 1e-10)));
        assertFalse("A different real part should not be equal", c.isEqualTo(new ComplexNumber(0.4, 1.0)));
        assertFalse("A different imaginary part should not be equal", c.isEqualTo(new ComplexNumber(0.3, 1.1)));
    }
}
