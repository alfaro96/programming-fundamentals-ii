package numbers;

/**
 * Represents an irrational number.
 * <p>
 * Since it cannot be expressed as an exact fraction, it inherits directly from
 * {@link Real} and resolves its operations using standard floating-point arithmetic.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Irrational extends Real {

    /**
     * Constructs a new {@link Irrational} number.
     *
     * @param value The decimal approximation of the irrational number.
     */
    public Irrational(double value) {
        super(value);
    }

    /**
     * Adds this irrational number to any other {@link Real}.
     * The result will always be "contaminated" and become another {@link Irrational}.
     *
     * @param other The generic addend.
     * @return A new approximation contained in an {@link Irrational}.
     */
    @Override
    public Real add(Real other) {
        return new Irrational(this.value + other.getValue());
    }

    /**
     * Subtracts a real number from this irrational number.
     *
     * @param other The generic subtrahend.
     * @return A new {@link Irrational} containing the difference.
     */
    @Override
    public Real subtract(Real other) {
        return new Irrational(this.value - other.getValue());
    }

    /**
     * Multiplies this irrational by another real number.
     *
     * @param other The generic multiplier.
     * @return The product stored in a new {@link Irrational}.
     */
    @Override
    public Real multiply(Real other) {
        return new Irrational(this.value * other.getValue());
    }

    /**
     * Divides this irrational by another real number.
     *
     * @param other The generic divisor.
     * @return The quotient stored in a new {@link Irrational}.
     */
    @Override
    public Real divide(Real other) {
        return new Irrational(this.value / other.getValue());
    }

    /**
     * Returns a textual representation clarifying that it is an approximation.
     *
     * @return {@code String} indicating the decimal value followed by the ({@link Irrational}) flag.
     */
    @Override
    public String toString() {
        return this.value + " (Irrational)";
    }
}
