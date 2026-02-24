package numbers;

/**
 * Represents a natural number (strictly positive integers starting from 1).
 * <p>
 * Inherits from {@link Integer} and adds restrictive logic in constructors and
 * operations to guarantee that no negative or zero natural numbers exist.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Natural extends Integer {

    /**
     * Constructs a new {@link Natural} number.
     *
     * @param value The positive whole number.
     */
    public Natural(int value) {
        super(value);
        if (value <= 0) {
            System.out.println("Warning: Natural numbers must be > 0. Defaulting to 1.");
            this.numerator = 1;
            this.value = 1.0; // Also update the inherited decimal value from Real
        }
    }

    /**
     * Adds this natural number to another.
     *
     * @param other The number to add.
     * @return A {@link Natural} or another type as resolved by the parent class.
     */
    @Override
    public Real add(Real other) {
        if (other instanceof Natural) {
            Natural n = (Natural) other;
            return new Natural(this.numerator + n.numerator);
        }
        return super.add(other);
    }

    /**
     * Subtracts another number from this natural number.
     * <p>
     * <b>Critical logic:</b> If subtracting two naturals results in a number less
     * than 1, the object loses its natural quality and the method performs a safe
     * "downcasting" by returning an {@link Integer}.
     * </p>
     *
     * @param other The subtrahend.
     * @return A {@link Natural} if the result > 0, otherwise an {@link Integer}.
     */
    @Override
    public Real subtract(Real other) {
        if (other instanceof Natural) {
            Natural n = (Natural) other;
            int result = this.numerator - n.numerator;

            if (result > 0) {
                return new Natural(result);
            } else {
                return new Integer(result); // Loses the natural property
            }
        }
        return super.subtract(other);
    }

    /**
     * Multiplies this natural number by another.
     *
     * @param other The multiplier.
     * @return The product as a {@link Natural} if operated against another {@link Natural}.
     */
    @Override
    public Real multiply(Real other) {
        if (other instanceof Natural) {
            Natural n = (Natural) other;
            return new Natural(this.numerator * n.numerator);
        }
        return super.multiply(other);
    }
}
