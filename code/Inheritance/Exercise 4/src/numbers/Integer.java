package numbers;

/**
 * Represents an integer number.
 * <p>
 * Inherits from {@link Rational} since an integer is logically a fraction
 * whose denominator is always 1.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Integer extends Rational {

    /**
     * Constructs a new {@link Integer} number.
     *
     * @param value The whole integer value.
     */
    public Integer(int value) {
        super(value, 1);
    }

    /**
     * Adds this integer to another number.
     *
     * @param other The number to add.
     * @return An {@link Integer} object if mathematically exact, or a broader type.
     */
    @Override
    public Real add(Real other) {
        if (other instanceof Integer) {
            Integer i = (Integer) other;
            return new Integer(this.numerator + i.numerator);
        }
        return super.add(other);
    }

    /**
     * Subtracts a number from this integer.
     *
     * @param other The subtrahend number.
     * @return The result as an {@link Integer} or the type returned by the parent.
     */
    @Override
    public Real subtract(Real other) {
        if (other instanceof Integer) {
            Integer i = (Integer) other;
            return new Integer(this.numerator - i.numerator);
        }
        return super.subtract(other);
    }

    /**
     * Multiplies this integer by another.
     *
     * @param other The multiplier.
     * @return An {@link Integer} if the operation is strictly between integers.
     */
    @Override
    public Real multiply(Real other) {
        if (other instanceof Integer) {
            Integer i = (Integer) other;
            return new Integer(this.numerator * i.numerator);
        }
        return super.multiply(other);
    }

    /**
     * Divides this integer by another.
     *
     * @param other The divisor.
     * @return An {@link Integer} if the remainder is 0, a {@link Rational} otherwise.
     */
    @Override
    public Real divide(Real other) {
        if (other instanceof Integer) {
            Integer i = (Integer) other;
            if (this.numerator % i.numerator == 0) {
                return new Integer(this.numerator / i.numerator);
            } else {
                return new Rational(this.numerator, i.numerator);
            }
        }
        return super.divide(other);
    }

    /**
     * Returns the integer value as a string, ignoring the denominator 1.
     *
     * @return A literal representation of the integer number.
     */
    @Override
    public String toString() {
        return String.valueOf(this.numerator);
    }
}