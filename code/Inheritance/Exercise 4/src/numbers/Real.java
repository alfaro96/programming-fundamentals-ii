package numbers;

/**
 * Represents the abstract base class for all real numbers in the system.
 * <p>
 * It defines the common contract for mathematical operations and encapsulates
 * the base decimal value. This class is designed to be extended, thus direct
 * instantiation is restricted.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public abstract class Real {

    /** The exact or approximate decimal value of the real number. */
    protected double value;

    /**
     * Constructs a new {@link Real} number.
     *
     * @param value The decimal format value of the number.
     */
    protected Real(double value) {
        this.value = value;
    }

    /**
     * Adds this real number to another provided real number.
     *
     * @param other The other {@link Real} object to add.
     * @return A new {@link Real} object containing the sum.
     */
    public abstract Real add(Real other);

    /**
     * Subtracts another real number from this real number.
     *
     * @param other The {@link Real} object acting as the subtrahend.
     * @return A new {@link Real} object containing the difference.
     */
    public abstract Real subtract(Real other);

    /**
     * Multiplies this real number by another real number.
     *
     * @param other The {@link Real} object to multiply by.
     * @return A new {@link Real} object containing the product.
     */
    public abstract Real multiply(Real other);

    /**
     * Divides this real number by another real number.
     *
     * @param other The {@link Real} object acting as the divisor.
     * @return A new {@link Real} object containing the quotient.
     */
    public abstract Real divide(Real other);

    /**
     * Retrieves the stored decimal value of the object.
     * Crucial for performing mathematical operations with unknown types within the hierarchy.
     *
     * @return The decimal value as a primitive {@code double}.
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Returns a string representation of the real number.
     *
     * @return The decimal value converted to a {@code String}.
     */
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}