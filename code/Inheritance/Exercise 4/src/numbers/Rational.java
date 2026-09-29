package numbers;

/**
 * Represents a rational number, which can be expressed as an exact fraction.
 * <p>
 * Implements precise fraction arithmetic operations when interacting with
 * other objects of its own family.
 * </p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class Rational extends Real {

    /** The top part of the fraction. */
    protected int numerator;

    /** The bottom part of the fraction. Its value must never be zero. */
    protected int denominator;

    /**
     * Constructs a new {@link Rational} number.
     *
     * @param numerator The numerator of the fraction.
     * @param denominator The denominator of the fraction.
     */
    public Rational(int numerator, int denominator) {
        super((double) numerator / denominator);
        this.numerator = numerator;
        this.denominator = denominator;
    }

    /**
     * Adds this rational number to another number.
     *
     * @param other The number to add.
     * @return A {@link Rational} if both are rational, otherwise an {@link Irrational}.
     */
    @Override
    public Real add(Real other) {
        if (other instanceof Rational) {
            Rational r = (Rational) other;
            int newNum = (this.numerator * r.denominator) + (r.numerator * this.denominator);
            int newDen = this.denominator * r.denominator;
            return new Rational(newNum, newDen);
        }
        return new Irrational(this.value + other.getValue());
    }

    /**
     * Subtracts a number from this rational number.
     *
     * @param other The number to subtract.
     * @return The resulting {@link Rational}, or {@link Irrational} if applicable.
     */
    @Override
    public Real subtract(Real other) {
        if (other instanceof Rational) {
            Rational r = (Rational) other;
            int newNum = (this.numerator * r.denominator) - (r.numerator * this.denominator);
            int newDen = this.denominator * r.denominator;
            return new Rational(newNum, newDen);
        }
        return new Irrational(this.value - other.getValue());
    }

    /**
     * Multiplies this rational number by another using cross-multiplication.
     *
     * @param other The multiplier factor.
     * @return A {@link Rational} or {@link Irrational} depending on the input type.
     */
    @Override
    public Real multiply(Real other) {
        if (other instanceof Rational) {
            Rational r = (Rational) other;
            return new Rational(this.numerator * r.numerator, this.denominator * r.denominator);
        }
        return new Irrational(this.value * other.getValue());
    }

    /**
     * Divides this rational number by another by multiplying by the reciprocal.
     *
     * @param other The divisor.
     * @return The quotient as a {@link Rational} or {@link Irrational}.
     */
    @Override
    public Real divide(Real other) {
        if (other instanceof Rational) {
            Rational r = (Rational) other;
            return new Rational(this.numerator * r.denominator, this.denominator * r.numerator);
        }
        return new Irrational(this.value / other.getValue());
    }

    /**
     * Visual representation in fraction format.
     *
     * @return A string format.
     */
    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }
}