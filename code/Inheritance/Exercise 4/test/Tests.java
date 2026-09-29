import numbers.Integer;
import numbers.*;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for the {@link Real} number hierarchy.
 * <p>
 * This class tests the mathematical operations, polymorphic behavior,
 * dynamic type resolution, and specific constraints (like non-zero denominators
 * and strictly positive natural numbers).
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Tests {

  private static final double DELTA = 0.00001; // Tolerance for double comparisons

  /**
   * Tests basic {@link Rational} arithmetic and ensures the returned type remains {@link Rational}
   * when operating between two fractions.
   */
  @Test
  public void testRationalAddition() {
    Rational r1 = new Rational(1, 2);
    Rational r2 = new Rational(1, 4);

    Real result = r1.add(r2);

    assertTrue("Result should be an instance of Rational", result instanceof Rational);
    assertEquals("Decimal value should match 0.75", 0.75, result.getValue(), DELTA);
  }

  /**
   * Tests the advanced polymorphic division of integers.
   * <p>
   * If the division is exact, it should return an {@link Integer}.
   * If it is not exact, it should dynamically return a {@link Rational}.
   * </p>
   */
  @Test
  public void testIntegerDivisionTypeShifting() {
    Integer i1 = new Integer(10);
    Integer i2 = new Integer(2);
    Integer i3 = new Integer(4);

    // Exact division
    Real exactResult = i1.divide(i2);
    assertTrue("Exact division should return an Integer", exactResult instanceof Integer);
    assertEquals("Value should be 5.0", 5.0, exactResult.getValue(), DELTA);

    // Inexact division
    Real inexactResult = i1.divide(i3);
    assertTrue("Inexact division should return a Rational", inexactResult instanceof Rational);
    assertEquals("Value should be 2.5", 2.5, inexactResult.getValue(), DELTA);
  }

  /**
   * Tests the strict constraints of the {@link Natural} class.
   * A {@link Natural} number instantiated with a zero or negative value should default to 1.
   */
  @Test
  public void testNaturalNumberConstraints() {
    Natural validNat = new Natural(5);
    assertEquals("Valid natural should retain its value", 5.0, validNat.getValue(), DELTA);

    Natural invalidNatZero = new Natural(0);
    assertEquals("Zero should default to 1", 1.0, invalidNatZero.getValue(), DELTA);

    Natural invalidNatNegative = new Natural(-10);
    assertEquals("Negative numbers should default to 1", 1.0, invalidNatNegative.getValue(), DELTA);
  }

  /**
   * Tests the polymorphic subtraction of {@link Natural} numbers.
   * <p>
   * If subtracting two {@link Natural} numbers results in a value less than 1,
   * the object should lose its {@link Natural} property and return an {@link Integer}.
   * </p>
   */
  @Test
  public void testNaturalSubtractionTypeShifting() {
    Natural n1 = new Natural(5);
    Natural n2 = new Natural(10);

    // Still a Natural
    Real positiveResult = n2.subtract(n1);
    assertTrue("Result > 0 should remain a Natural", positiveResult instanceof Natural);
    assertEquals("Value should be 5.0", 5.0, positiveResult.getValue(), DELTA);

    // No longer a Natural, should drop to Integer
    Real negativeResult = n1.subtract(n2);
    assertTrue("Result <= 0 should downgrade to an Integer", negativeResult instanceof Integer);
    assertFalse("Result should NO LONGER be a Natural", negativeResult instanceof Natural);
    assertEquals("Value should be -5.0", -5.0, negativeResult.getValue(), DELTA);
  }

  /**
   * Tests operations combining {@link Rational} and {@link Irrational} numbers.
   * <p>
   * Any operation involving an {@link Irrational} number should "contaminate" the result,
   * forcing it to return an {@link Irrational} object.
   * </p>
   */
  @Test
  public void testIrrationalContamination() {
    Rational rat = new Rational(1, 2); // 0.5
    Irrational pi = new Irrational(Math.PI);

    Real resultAdd = rat.add(pi);
    assertTrue("Rational + Irrational should result in Irrational", resultAdd instanceof Irrational);
    assertEquals("Value should be 0.5 + Pi", 0.5 + Math.PI, resultAdd.getValue(), DELTA);

    Real resultMult = pi.multiply(rat);
    assertTrue("Irrational * Rational should result in Irrational", resultMult instanceof Irrational);
    assertEquals("Value should be Pi * 0.5", Math.PI * 0.5, resultMult.getValue(), DELTA);
  }
}
